package kitchenpos.menus.tobe.vo;

import org.thymeleaf.util.StringUtils;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;

@Embeddable
@Access(AccessType.FIELD)
public class MenuGroupName {
    private final String name;

    protected MenuGroupName() {
        this.name = null;
    }

    public MenuGroupName(String name) {
        validMenuGroupName(name);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    private void validMenuGroupName(String name) {
        if (StringUtils.isEmptyOrWhitespace(name)) {
            throw new IllegalArgumentException();
        }
    }
}
