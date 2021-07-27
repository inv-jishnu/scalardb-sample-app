package contact;

import java.io.IOException;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.scalar.db.api.*;
import com.scalar.db.exception.transaction.TransactionException;
import com.scalar.db.io.Key;
import com.scalar.db.io.TextValue;
import com.scalar.db.service.TransactionModule;
import com.scalar.db.service.TransactionService;

import java.util.List;
import java.util.Optional;

public class ContactTransactionService extends Contact{
    private final TransactionService service;
    public ContactTransactionService() throws IOException {
        Injector injector = Guice.createInjector(new TransactionModule(dbConfig));
        service = injector.getInstance(TransactionService.class);
        service.with(NAMESPACE, TABLE_NAME);
    }

    @Override
    void add(String name, String email, String phone) throws Exception {
        DistributedTransaction tx = service.start();
        Get get = new Get(new Key(new TextValue(NAME,name)), new Key(new TextValue(EMAIL, email)));
        Optional<Result> user = tx.get(get);

        if(user.isPresent()){
            System.out.println("Data exists in db for that value");
        }else{
            Put put = new Put(new Key(new TextValue(NAME, name)), new Key(new TextValue(EMAIL, email))).withValue(new TextValue(PHONE, phone));
            tx.put(put);
            System.out.println("Data successfully added");
        }

        tx.commit();


    }

    @Override
    void getPhone(String name, String email) throws TransactionException {
        DistributedTransaction tx = service.start();
        Get get = new Get(new Key(new TextValue(NAME, name)), new Key(new TextValue(EMAIL, email)));
        Optional<Result> result = tx.get(get);
        if(result.isPresent()){
          if (result.get().getValue(PHONE).isPresent()) {
                System.out.println("Phone : " + ((TextValue) result.get().getValue(PHONE).get()).getString().get());
            }

        }else {
            System.out.println("Matching ID and NAME not found");
        }
    }

    @Override
    void getList(String name) throws TransactionException {
        DistributedTransaction tx = service.start();
        Scan scan = new Scan(new Key(new TextValue(NAME, name)));
       
        List<Result> result = tx.scan(scan);
        if(result.isEmpty()){
            System.out.println("No results found");
        }else{
          System.out.println("Data available is listed below");
          result.forEach(
              result1 -> {
                if (result1.getValue(EMAIL).isPresent()) {
                  System.out.println("Email : " + ((TextValue) result1.getValue(EMAIL).get()).getString().get());
                }
                if (result1.getValue(PHONE).isPresent()) {
                  System.out.println("Phone : " + ((TextValue) result1.getValue(PHONE).get()).getString().get() + "\n");
                }
              });
       }
        

    }
}
