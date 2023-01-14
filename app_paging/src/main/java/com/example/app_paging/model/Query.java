package com.example.app_paging.model;

public class Query {
    private static String searchQueryStr;

    public static String getSearchQueryStr() {
        return searchQueryStr;
    }

    public static void setSearchQueryStr(String searchQueryStr) {
        Query.searchQueryStr = searchQueryStr;
    }
}
