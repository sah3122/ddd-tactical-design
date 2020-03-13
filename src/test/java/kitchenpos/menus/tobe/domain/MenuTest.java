package kitchenpos.menus.tobe.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
/**
 * Menu는 번호와 이름, 가격, MenuProducts를 가진다.
 * Menu는 특정 MenuGroup에 속한다.
 */
class MenuTest {

    @DisplayName("메뉴 정상 생성")
    @Test
    void create() {
        String name = "한마리 치킨";
        BigDecimal price = BigDecimal.valueOf(16_000L);
        Long menuGroupId = 1L;
        Long menuProductId = 1L;
        long quantity = 3;

        List<MenuProduct> menuProducts = Arrays.asList(new MenuProduct(menuProductId, quantity));

        Menu menu = new Menu(name, price, menuGroupId, menuProducts);

        assertAll(
                () -> assertThat(menu.getMenuGroupId()).isEqualTo(menuGroupId),
                () -> assertThat(menu.getName()).isEqualTo(name),
                () -> assertThat(menu.getPrice()).isEqualTo(price),
                () -> assertThat(menu.getMenuProducts().list()).containsAll(menuProducts)
        );
    }
}
