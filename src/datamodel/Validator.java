package datamodel;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.regex.Pattern;

/**
 * Verify input for valid name or contact specifications and
 * return validated results. Class is used by {@link DataFactory} methods
 * to create objects.
 *
 * @version {@value application.package_info#Version}
 * @author {@value application.package_info#Author}
 */
public final class Validator {

    // Pattern to validate last names (must start with uppercase letter)
    private final Pattern lastNamePattern = Pattern.compile("^[A-Z][A-Za-z-\\s]*$");
    // Pattern to validate first names (empty or start with uppercase letter)
    private final Pattern firstNamePattern = Pattern.compile("^$|^[A-Z][A-Za-z-\\s.]*$");
    // Combine validation of first and last name parts
    private final BiFunction<String, String, Boolean> validateNameParts =
        (first, last) -> last != null && first != null
            && lastNamePattern.matcher(last).matches()
            && firstNamePattern.matcher(first).matches();

    // Pattern to validate email addresses
    private final Pattern emailPattern = Pattern.compile(
        "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z0-9_]+$",
        Pattern.CASE_INSENSITIVE
    );

    // Pattern to validate phone or fax numbers (at least 6 characters)
    private final Pattern phonePattern = Pattern.compile(
        "^(?:phone:|fax:|\\+[0-9]+)?[\\s0-9()\\-]{6,}$",
        Pattern.CASE_INSENSITIVE
    );

    // Record to hold first and last name parts
    public record NameParts(String firstName, String lastName) { }

    /**
     * Split and validate a single-string name into first- and last-name parts.
     *
     * @param name single-string name to split
     * @return Optional<NameParts> if valid, otherwise empty
     */
    public Optional<NameParts> validateName(String name) {
        return Optional.ofNullable(name)
            .map(n -> {
                String first = "", last = "";
                // split on comma or semicolon
                String[] parts = n.split("[,;]");
                if (parts.length > 1) {
                    last  = parts[0];
                    first = parts[1];
                } else {
                    // no separator: split on whitespace
                    for (String s : n.trim().split("\\s+")) {
                        if (!last.isEmpty()) {
                            first += (first.isEmpty() ? "" : " ") + last;
                        }
                        last = s;
                    }
                }
                first = trim(first);
                last  = trim(last);
                return validateNameParts.apply(first, last)
                    ? new NameParts(first, last)
                    : null;
            })
            .filter(np -> np != null);
    }

    /**
     * Validate email or phone contact syntax.
     *
     * @param contact contact string to verify
     * @return Optional<String> containing trimmed contact if valid
     */
    public Optional<String> validateContact(String contact) {
        return Optional.ofNullable(contact)
            .map(String::trim)
            .filter(c -> emailPattern.matcher(c).matches()
                || phonePattern.matcher(c).matches());
    }

    /**
     * Trim whitespace, commas, semicolons, and quotes from the ends.
     *
     * @param s input string
     * @return trimmed string
     */
    private String trim(String s) {
        if (s == null) {
            return null;
        }
        s = s.replaceAll("^[\\s\"',;]+", "");
        s = s.replaceAll("[\\s\"',;]+$", "");
        return s;
    }
}
