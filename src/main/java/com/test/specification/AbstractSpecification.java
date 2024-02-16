package com.test.specification;

import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.jpa.domain.Specification;

public abstract class AbstractSpecification {

  protected AbstractSpecification() {
    throw new UnsupportedOperationException("this is an abstract utility class");
  }

  public static String like(String element) {
    return Optional.ofNullable(element).map(e -> "%" + e + "%").orElse("%%");
  }

  public static <T, U> Optional<Specification<U>> transformer(T object, Function<T, Specification<U>> mapper) {
    return Optional.ofNullable(object)
        .map(e -> Optional.ofNullable(mapper.apply(e)))
        .orElse(Optional.empty());
  }

  public static <T> Specification<T> distinct() {
    return (root, query, builder) -> {
      query.distinct(true);
      return builder.isNotNull(root.get("id"));
    };
  }

}
