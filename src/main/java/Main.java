import View.View;

import java.io.IOException;

/**
 * Created by hungm on 27/09/2016.
 */
public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        View view = new View();
        int choice = 0;
        do {
            choice = view.ViewHome();
            switch (choice)
            {
                case 1: view.ViewListStudent(); break;
                case 2: view.AddStudent(); break;
                case 3: view.EditStudent(); break;
                case 4: view.DeleteStudent(); break;
                case 5: view.SearchHome(); break;
                case 6: view.SortHome(); break;
                case 7: view.Statistical(); break;
                case 8: view.Exit();  break;
            }
        }while (choice != 8);
    }
}
