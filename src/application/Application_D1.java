package application;

import java.util.List;
import java.util.Optional;

import datamodel.DataFactory;
import datamodel.Customer;

/**
 * Class that implements the {@link Runner} interface and executes tasks of the D1 assignment.
 *
 * @version <code style=color:green>{@value application.package_info#Version}</code>
 * @author <code style=color:blue>{@value application.package_info#Author}</code>
 */
public class Application_D1 implements Runner {

    @Override
    public void run(String[] args) {
        DataFactory df = DataFactory.getInstance();

        List<Customer> customers = List.of(
            df.createCustomer("Eric", "Meyer", "eric98@yahoo.com", "eric98@yahoo.com", "(030) 3945-642298"),
            df.createCustomer("Bayer, Anne", "anne24@yahoo.de", "(030) 3481-23352", "fax: (030)23451356"),
            df.createCustomer(" Tim ", " Schulz-Mueller ", "tim2346@gmx.de"),
            df.createCustomer("Nadine-Ulla Blumenfeld", "+49 152-92454"),
            df.createCustomer("Khaled Saad Mohamed Abdelalim", "+49 1524-12948210"),
            df.createCustomer("Mandy Mondschein", "locomandy@gmx.de"),
            df.createCustomer("Meissner, Susanne", "nobody@gmx.de")
        ).stream()
         .flatMap(Optional::stream)
         .toList();

        System.out.println(String.format(
            "(%d) Customer objects built.%n" +
            "(%d) Article objects built.%n" +
            "(%d) Order objects built.%n---%n",
            customers.size(), 0, 0));

        System.out.println("Kunden:");
        System.out.println(Application.printCustomers(customers));
    }

    public static void main(String[] args) {
        new Application_D1().run(args);
    }
}
