package kitchenpos.menus.application;

import kitchenpos.menus.tobe.domain.*;
import kitchenpos.menus.tobe.dto.MenuProductRequestDto;
import kitchenpos.menus.tobe.dto.MenuRequestDto;
import kitchenpos.menus.tobe.dto.MenuResponseDto;
import kitchenpos.products.tobe.domain.Product;
import kitchenpos.products.tobe.domain.ProductRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class MenuService {
    private final MenuRepository menuRepository;
    private final MenuProductRepository menuProductRepository;
    private final MenuGroupRepository menuGroupRepository;
    private final ProductRepository productRepository;

    public MenuService(MenuRepository menuRepository, MenuProductRepository menuProductRepository, MenuGroupRepository menuGroupRepository, ProductRepository productRepository) {
        this.menuRepository = menuRepository;
        this.menuProductRepository = menuProductRepository;
        this.menuGroupRepository = menuGroupRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public MenuResponseDto create(final MenuRequestDto menuRequestDto) {
        Menu menu = menuRequestDto.toEntity();

        MenuGroup findMenuGroup = menuGroupRepository.findById(menuRequestDto.getMenuGroupId())
                .orElseThrow(() -> new IllegalArgumentException());

        menu.changeMenuGroup(findMenuGroup);

        List<MenuProductRequestDto> menuProductRequestDtos = menuRequestDto.getMenuProducts();

        final List<MenuProduct> menuProducts = new ArrayList<>();

        BigDecimal sum = BigDecimal.ZERO;
        for (final MenuProductRequestDto menuProductRequestDto : menuProductRequestDtos) {
            final Product product = productRepository.findById(menuProductRequestDto.getProductId())
                    .orElseThrow(IllegalArgumentException::new);
            MenuProduct menuProduct = new MenuProduct(product, menuProductRequestDto.getQuantity());
            menuProducts.add(menuProduct);
            sum = sum.add(menuProduct.getSumPrice());
        }

        menu.validPriceLessthanMenuProductsSum(sum);

        final Menu savedMenu = menuRepository.save(menu);

        for (final MenuProduct menuProduct : menuProducts) {
            menuProduct.changeMenu(savedMenu);
            menuProductRepository.save(menuProduct);
        }
        savedMenu.changeMenuProducts(menuProducts);

        return new MenuResponseDto(savedMenu);
    }

    public List<MenuResponseDto> list() {
        List<Menu> menus = menuRepository.findMenuByAll();

//        for (final Menu menu : menus) {
//            menu.setMenuProducts(menuProductRepository.findAllByMenuId(menu.getId()));
//        }

        return menus.stream()
                .map(MenuResponseDto::new)
                .collect(Collectors.toList());
    }
}
