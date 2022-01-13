package com.maximalibri.bookreview.config;

import java.util.List;

import static java.util.Arrays.asList;

class SecurityURLs {
    final static List<UrlRoles> URLS = asList(
            new UrlRoles("/book-review/user/(.*)", asList(RoleName.ROLE_USER))
    );

    private SecurityURLs() {
    }
}
