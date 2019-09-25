//package com.innodox.library.core;
//
//
///**
// * ADATBÁZIS FELTÖLTÉS INDíTÁSKOT
// */
//
//
//import com.innodox.library.entity.Author;
//import com.innodox.library.entity.Book;
//import com.innodox.library.entity.Category;
//import com.innodox.library.entity.Password;
//import com.innodox.library.entity.User;
//import com.innodox.library.repo.AuthorRepo;
//import com.innodox.library.repo.BookRepo;
//import com.innodox.library.repo.CategoryRepo;
//import com.innodox.library.repo.UserRepo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.stereotype.Component;
//
//import java.util.*;
//
//@Component
//public class DatabaseLoader implements ApplicationRunner {
//
//    private UserRepo userRepo;
//    private BookRepo bookRepo;
//    private CategoryRepo categoryRepo;
//    private AuthorRepo authorRepo;
//
//    @Autowired
//    public DatabaseLoader(UserRepo userRepo, BookRepo bookRepo, CategoryRepo categoryRepo, AuthorRepo authorRepo) {
//        this.userRepo = userRepo;
//        this.bookRepo = bookRepo;
//        this.categoryRepo = categoryRepo;
//        this.authorRepo = authorRepo;
//    }
//
//
//    @Override
//    public void run(ApplicationArguments args) throws Exception {
//
//
//        Category category1 = new Category("Romantikus");
//        Category category2 = new Category("Sci-fi");
//        Category category3 = new Category("Thriller");
//        Category category4 = new Category("Novella");
//        List<Category> categories = Arrays.asList(
//                category1, category2, category3, category4
//        );
//        categoryRepo.saveAll(categories);
//
//        Author author1 = new Author();
//        author1.setFirstName(" Rózsa");
//        author1.setLastName("G. Hajnóczy");
//        author1.setAge(30);
//        author1.setBooks(new ArrayList<>());
//        authorRepo.save(author1);
//
//        Author author2 = new Author();
//        author2.setFirstName("Troy");
//        author2.setLastName("Brennan");
//        author2.setAge(40);
//        author2.setBooks(new ArrayList<>());
//        authorRepo.save(author2);
//
//        Author author3 = new Author();
//        author3.setFirstName("Katy");
//        author3.setLastName("Evans");
//        author3.setAge(40);
//        author3.setBooks(new ArrayList<>());
//        authorRepo.save(author3);
//
//        Author author4 = new Author();
//        author4.setFirstName("Audrey");
//        author4.setLastName("Carlan");
//        author4.setAge(40);
//        author4.setBooks(new ArrayList<>());
//        authorRepo.save(author4);
//
//        Book book1 = new Book();
//        book1.setTitle("Bengjáli tűz");
//        book1.setPublisher("Móra");
//        book1.setAuthor(author1);
//        book1.setQuantity(7);
//        book1.setCategory(category1);
//        book1.setPreface("„Minden hazugság egy párhuzamos világot teremt. Egy világot, ahol az a hazugság igaz.”\n" +
//                "(Momus)");
//        book1.setContent("TÖRTÉNETÜNK kezdetén egy srác ül az ágyon, és a telefonja kijelzője fölé görnyed a sötétben.\n" +
//                "Riccardo éppen gépel… Azt mondta, talán a barátnője is jön.\n" +
//                "Anna éppen gépel… te jó ég remélem nem\n" +
//                "Anna éppen gépel... szebben énekel, és szexibb\n" +
//                "Riccardo éppen gépel... 1");
//        bookRepo.save(book1);
//        author1.getBooks().add(book1);
//
//        Book book2 = new Book();
//        book2.setTitle("Sparrow");
//        book2.setPublisher("Park könyvkiadó");
//        book2.setAuthor(author2);
//        book2.setQuantity(7);
//        book2.setCategory(category1);
//        book2.setPreface("„A név, amit mindenki ismer Dél-Bostonban. Egy néhai maffiózó fia. A jeges kék szemű szívrabló.");
//        book2.setContent("A \"lerendező\", aki ebben a városban bárkit simán magasba emel vagy porba tipor. \n" +
//                "Ja, és egyébként az újdonsült férjem. \n" +
//                "Sparrow Raynes \n" +
//                "Ez meg én volnék. A kutya sem bírta megjegyezni a nevemet, amíg Troy be nem csörtetett az életembe. \n" +
//                "Aztán kalitkába zárt. \n" +
//                "Foglyul ejtett. \n" +
//                "És kiiktatott minden lehetőséget, hogy elmenekülhessek onnét, ahol fölcseperedtünk. \n" +
//                "Úgy is mondhatnám, hogy Troy Brennan megnyirbálta a szárnyaimat. \n" +
//                "Vannak álmaim, méghozzá merészek, de kétlem, hogy valaha is engedi valóra váltanom őket. Fogalmam sincs, miért döntött úgy, hogy feleségül vesz. Azt azonban tudom, hogy ha kiakasztom ezt a pasast, annak nem lesz jó vége. De nem ám. \n" +
//                "\n" +
//                "L. J. Shen a világon mindenütt lakott már, és végül Észak-Kaliforniában állapodott meg, leginkább a borok miatt. Megélhetésként ír és szórakozásként olvas. Egy fiúgyermek büszke anyukája, egy volt kóbor macska gazdija, valamint egy nagyon türelmes férj tuti felesége. \n" +
//                "\n" +
//                "Kemény, de élvezni fogod! Hagyd, hogy elraboljon!");
//        bookRepo.save(book2);
//        author2.getBooks().add(book2);
//
//        Book book3 = new Book();
//        book3.setTitle("Real");
//        book3.setPublisher("Panem kiadó");
//        book3.setAuthor(author3);
//        book3.setQuantity(7);
//        book3.setCategory(category1);
//        book3.setPreface("„Forró. Szexi. Erőteljes. Valós. A perzselő nemzetközi bestseller, amely pőrére vetkőztet mindent, amit a szenvedélyről hittél.");
//        book3.setContent("Remington Tate rossz fiú hírében áll a ringben és a ringen kívül is. Gránitkemény testétől, nyers, állati erejétől minden női rajongója megvadul. Ám attól fogva, hogy a pillantásuk találkozik, neki csak egyetlen nő kell: Brook Dumas. A vágya tiszta, mindent felemésztő, VALÓS. \n" +
//                "Miután szerződtetik, hogy Remy tökéletes testét olajozott gépként működtesse, Brooke úgy érzi, megtalálta álmai jól fizető sportterapeuta melóját. De mialatt a pinceharc veszedelmes világát járja Remyvel és csapatával, az ő testében is felhorgad a zsigeri vágy. Könnyed flörtnek indult Brooke és Remy kapcsolata, de csakhamar erotikus megszállottságban csúcsosodik mindkettőjüknél, és még többel kecsegtet. \n" +
//                "De izzó szenvedélyük mögött ott lapul a sötétség, és felmerül a kérdés, hogy amikor fény derül Remy legmélyebb titkára, vagy amikor Brooke családi kötelezettsége tetteket követel, vajon képesek lesznek-e egymás mellett kitartani? Vagy minden, ami annyira igazinak tűnt, szétfoszlik, akár a káprázat? \n");
//        bookRepo.save(book3);
//        author3.getBooks().add(book3);
//
//        Book book4 = new Book();
//        book4.setTitle("Calendar Girlz");
//        book4.setPublisher("Líra kiadó");
//        book4.setAuthor(author4);
//        book4.setQuantity(7);
//        book4.setCategory(category1);
//        book4.setPreface("„Mia Saunders tizenkét hónapos utazása Hollywoodba, New York Citybe és végül Aspenbe repíti.");
//        book4.setContent("Októberben Mia új életet kezd, és egy híres, naponta jelentkező tévéműsorban lesz háziasszonya az Élő Szépség betétnek. Párja még a fogság utóhatásaival küzd, de szerencsére közös erővel leküzdik a démonokat. \n" +
//                "Ezután New Yorkba utazik, ahol a háláról forgat. Minden álma valóra válni látszik... kivéve egyet. \n" +
//                "Decemberben az álomszerű Aspenben találja magát, ahol egy helyi művészről készít kisfilmet, meglehetősen egyedi körülmények között. \n" +
//                "Kedves olvasó, készülj, mert Mia kalandja a várva várt végéhez közeledik! \n" +
//                "\n" +
//                "Ébren tart a vágy, az izgalom, a szenvedély. Ez az INSOMNIA könyvek birodalma. Ne várd a hajnalt, olvasd ki az éjszakát!");
//        bookRepo.save(book4);
//        author4.getBooks().add(book4);
//
////        List<Book> books = Arrays.asList(
////                book1, book2, book3, book4
////        );
////        bookRepo.saveAll(books);
////
////        List<Book> bookList1 = Arrays.asList(
////                book1, book2, book3
////        );
//////        author1.setBooks(bookList1);
//////        category1.setBooks(bookList1);
////        List<Book> bookList2 = Arrays.asList(
////                book4, book2, book3
////        );
//////        category2.setBooks(bookList2);
////        List<Book> bookList3 = Arrays.asList(
////                book1, book2, book4
////        );
//////        category3.setBooks(bookList3);
//////        category4.setBooks(bookList3);
////        author1.setBooks(bookList1);
//
//        List<User> users = Arrays.asList(
//                new User("skovi", "sanyi.kovacs@gmail.com", "Sanyi", "kovács", new Password("pas"), new String[]{"ROLE_ADMIN", "ROLE_USER"}),
//                new User("sferi", "feri.sos@t-online.com", "Feri", "Sós", new Password("pas"), new String[]{"ROLE_USER"}),
//                new User("ktomi", "tamas.kis@gmail.com", "Tomi", "kis", new Password("pas"), new String[]{"ROLE_USER"})
//        );
//        userRepo.saveAll(users);
//    }
//}
