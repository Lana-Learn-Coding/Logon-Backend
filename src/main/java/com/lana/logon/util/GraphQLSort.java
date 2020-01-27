package com.lana.logon.util;

import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

public class GraphQLSort extends Sort {

    protected GraphQLSort(List<Order> orders) {
        super(orders);
    }

    public static Sort of(List<String> sorts) {
        String separator = "__";
        ArrayList<Order> orders = new ArrayList<>();

        if (sorts == null) return Sort.unsorted();

        for (String sort : sorts) {
            // get the last separator only in case there are
            // separator in property name like: some__property__name__asc
            int lastSeparatorIndex = sort.lastIndexOf(separator);

            // no separator found.
            if (lastSeparatorIndex == -1 || lastSeparatorIndex == sort.length() - separator.length()) {
                orders.add(Order.asc(sort));
                continue;
            }

            String property = sort.substring(0, lastSeparatorIndex);
            String direction = sort.substring(lastSeparatorIndex + separator.length());
            switch (direction) {
                case "asc":
                    orders.add(Order.asc(property));
                    break;
                case "desc":
                    orders.add(Order.desc(property));
                    break;
                default:
                    // In case the direction is neither "asc" or "desc",
                    // then treat the whole string as an property
                    orders.add(Order.asc(property.concat(separator).concat(direction)));
            }
        }

        return Sort.by(orders);
    }
}
