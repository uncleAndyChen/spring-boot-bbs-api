package bbs.api.biz.enumeration;

public enum UserStarAndPraiseMapMapTypeEnum {
    star(1,"收藏"),
    praise(2,"点赞");

    private int index;
    private String name;

    UserStarAndPraiseMapMapTypeEnum(int index, String name) {
        this.index = index;
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static UserStarAndPraiseMapMapTypeEnum getEnumByIndex(int index) {
        for (UserStarAndPraiseMapMapTypeEnum result : UserStarAndPraiseMapMapTypeEnum.values()) {
            if (index == result.index) {
                return result;
            }
        }
        return null;
    }

    public static UserStarAndPraiseMapMapTypeEnum getEnumByName(String name) {
        for (UserStarAndPraiseMapMapTypeEnum result : UserStarAndPraiseMapMapTypeEnum.values()) {
            if (name.equals(result.getName())) {
                return result;
            }
        }

        return null;
    }
}
