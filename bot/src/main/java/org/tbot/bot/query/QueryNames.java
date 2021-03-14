package org.tbot.bot.query;

public enum QueryNames {
    USER_SEARCH_BY_CHAT_ID("getUserByChatId", new String[]{"chatId"}, false),
    USERS_SEARCH_BY_CITY_ID("getUsersByCityId", new String[]{"cityId"}, true),
    USERS_SEARCH_BY_ROLE_AND_CITY_ID("getUsersByRoleAndCityId", new String[]{"role", "cityId"}, true),
    CITY_SEARCH_BY_CITY_NAME_AND_AREA("getCityByCityNameAndArea", new String[]{"cityName", "area"}, false);

    private final String queryName;
    private final String[] param;
    private final boolean isMultiple;

    QueryNames(final String queryName, final String[] param, final boolean isMultiple) {
        this.queryName = queryName;
        this.param = param;
        this.isMultiple = isMultiple;
    }

    public String getQueryName() {
        return queryName;
    }

    public String[] getParam() {
        return param;
    }

    public boolean isMultiple() {
        return isMultiple;
    }
}
