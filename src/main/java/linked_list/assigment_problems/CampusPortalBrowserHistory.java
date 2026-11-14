package linked_list.assigment_problems;

import java.util.ArrayList;
import java.util.List;

public class CampusPortalBrowserHistory {

    public static void main(String[] args) {
        BrowserHistory browser = new BrowserHistory("srm.edu");
        browser.visit("a.com");
        browser.visit("b.com");
        browser.visit("c.com");

        List<String> output = new ArrayList<>();
        output.add(browser.back(1));
        output.add(browser.back(1));
        output.add(browser.forward(1));
        browser.visit("d.com");
        output.add(browser.forward(2));
        output.add(browser.back(2));
        output.add(browser.back(7));

        System.out.println(String.join(", ", output));
    }
}