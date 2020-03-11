package kitchenpos.menus.tobe.domain;

import kitchenpos.menus.tobe.vo.MenuGroupName;

import javax.persistence.*;

@Entity
public class MenuGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private MenuGroupName menuGroupName;

    protected MenuGroup() {
    }

    public MenuGroup(String name) {
        this.menuGroupName = new MenuGroupName(name);
    }

    public Long getId() {
        return id;
    }

    public String getMenuGroupName() {
        return menuGroupName.getName();
    }
}
