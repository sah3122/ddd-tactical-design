package kitchenpos.menus.tobe.dto;


import kitchenpos.menus.tobe.domain.MenuProduct;

public class MenuProductRequestDto {
    private Long productId;
    private long quantity;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }
}
