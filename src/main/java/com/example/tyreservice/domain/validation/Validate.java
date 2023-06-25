package com.example.tyreservice.domain.validation;

import java.time.LocalDate;
import java.util.Map;

import com.google.common.base.Strings;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Validate {

	public static class InvalidValueException extends RuntimeException {

		private static final long serialVersionUID = 5258767135657839428L;

		public InvalidValueException(final String name, final String currentValue, final String message) {
			super("The value " + name + " was received with value " + currentValue + ". " + message);
		}
	}


	public static final String NOT_AVALAIBLE = "Name not available (hope for stacktrace)";

	public static <T> T notNull(final T o) {

		return notNull(o, NOT_AVALAIBLE);
	}

	public static <T> T notNull(final T o, final String name) {

		if (o == null) {

			throw new InvalidValueException(name, null, "This argument can't be null");
		}

		return o;
	}

	public static String notEmpty(final String s) {

		return notEmpty(s, NOT_AVALAIBLE);
	}

	public static String notEmpty(final String s, final String name) {

		if (Strings.isNullOrEmpty(s)) {

			throw new InvalidValueException(name, s, "This argument can't be empty");
		}

		return s;
	}

	public static long strictlyPositive(final long l) {

		return strictlyPositive(l, NOT_AVALAIBLE);
	}

	public static int strictlyPositive(final int l) {

		return (int) strictlyPositive(l, NOT_AVALAIBLE);
	}

	public static long strictlyPositive(final long l, final String name) {

		if (l < 1) {

			throw new InvalidValueException(name, String.valueOf(l), "This argument must be at least 1");
		}

		return l;
	}

	public static int notNegative(final int l) {

		return (int) notNegative(l, NOT_AVALAIBLE);
	}

	public static long notNegative(final long l, final String name) {

		if (l < 0) {

			throw new InvalidValueException(name, String.valueOf(l), "This argument must be at least 0");
		}

		return l;
	}

	public static LocalDate notInPast(final LocalDate date) {

		return notInPast(date, NOT_AVALAIBLE);
	}


	public static LocalDate notInPast(final LocalDate date, final String name) {

		if ( notNull(date).isBefore(LocalDate.now())) {

			throw new InvalidValueException(name, date.toString(), "This argument can't be in the past");
		}

		return date;
	}

	public static <K,V> Map<K,V> notEmpty(final Map<K,V> map) {

		return notEmpty(map, NOT_AVALAIBLE);
	}

	public static <K,V> Map<K,V> notEmpty(final Map<K,V> map, final String name) {

		if (notNull(map).isEmpty()) {

			throw new InvalidValueException(name, map.toString(), "This argument can't be empty");
		}

		return map;
	}

	public static <T, U extends T> void areEquals(final T o1, final U o2) {

		if ( ! notNull(o1).equals(notNull(o2))) {

			throw new InvalidValueException("Not available", o1 + " <> " + o2, "Objects are not equals");
		}
	}
}
