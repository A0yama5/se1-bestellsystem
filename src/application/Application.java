package application;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import datamodel.Customer;

/**
 * CLI entry point and helper for printing Customer tables.
 *
 * @version <code style=color:green>{@value application.package_info#Version}</code>
 * @author <code style=color:blue>{@value application.package_info#Author}</code>
 */
public class Application implements Runner {

    private Application() { }

    public static void main(String[] args) {
        Runner application = new Application_D1();
        String className = application.getClass().getSimpleName();
        String moduleName = Application.class.getModule().getName();
        System.out.println(
            Optional.ofNullable(moduleName)
                .map(m -> String.format("Hello, %s (%s, modular)", m, className))
                .orElse(String.format("Hello, se1-bestellsystem (%s)", className))
        );
        application.run(args);
    }

    @Override
    public void run(String[] args) {
        Arrays.stream(args)
            .map(arg -> String.format(" - arg: %s", arg))
            .forEach(System.out::println);
    }

    /**
     * Builds an ASCII table of customers.
     */
    public static StringBuilder printCustomers(List<Customer> customers) {
        StringBuilder sb = new StringBuilder();
        // Define column widths and padding
        int idWidth       = 10;
        int nameWidth     = 33;
        int contactWidth  = 33;
        int padding       = 2; // one space on each side in the format string

        // Prepare horizontal line (including padding)
        String line = "+"
            + "-".repeat(idWidth       + padding)
            + "+"
            + "-".repeat(nameWidth     + padding)
            + "+"
            + "-".repeat(contactWidth  + padding)
            + "+";

        // Header
        sb.append(line).append("\n");
        sb.append(String.format(
            "| %" + idWidth + "s | %-" + nameWidth + "s | %-" + contactWidth + "s |",
            "Kund.-ID", "Name", "Kontakt"
        )).append("\n");
        sb.append(line).append("\n");

        // Rows
        for (Customer c : customers) {
            String id = String.format("%" + idWidth + "d", c.getId());

            String name = c.getLastName() + ", " + c.getFirstName();
            if (name.length() > nameWidth) {
                name = name.substring(0, nameWidth);
            }
            name = String.format("%-" + nameWidth + "s", name);

            int cnt = c.contactsCount();
            String firstContact = c.getContacts().iterator().next();
            String ext = cnt > 1 ? String.format(", (+%d contacts)", cnt - 1) : "";
            String contact = firstContact + ext;
            if (contact.length() > contactWidth) {
                contact = contact.substring(0, contactWidth);
            }
            contact = String.format("%-" + contactWidth + "s", contact);

            sb.append(String.format("| %s | %s | %s |", id, name, contact))
              .append("\n");
        }

        // Footer
        sb.append(line);
        return sb;
    }
}
