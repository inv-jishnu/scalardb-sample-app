package com.scalar.contact;

import java.io.IOException;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.scalar.db.api.*;
import com.scalar.db.exception.storage.ExecutionException;
import com.scalar.db.io.Key;
import com.scalar.db.io.TextValue;
import com.scalar.db.service.StorageModule;
import com.scalar.db.service.StorageService;
import java.util.Optional;

public class ContractStorageService extends Contact{

    private final StorageService storageService;

    public ContractStorageService() throws IOException {

        Injector injector2 = Guice.createInjector(new StorageModule(dbConfig));
        storageService = injector2.getInstance(StorageService.class);
        storageService.with(NAMESPACE, TABLE_NAME);
    }

    @Override
    void add(String name, String email, String phone) throws Exception {
        Get get = new Get(new Key(new TextValue(NAME,name)), new Key(new TextValue(EMAIL, email)));
        Optional<Result> user = storageService.get(get);
        if(user.isPresent()){
            System.out.println("Data exists in db for that value");
        }else{
            Put put = new Put(new Key(new TextValue(NAME, name)), new Key(new TextValue(EMAIL, email))).withValue(new TextValue(PHONE, phone));
            storageService.put(put);
            System.out.println("Data successfully added");
        }


    }

    @Override
    void getPhone(String name, String email) throws ExecutionException {
        Get get = new Get(new Key(new TextValue(NAME, name)), new Key(new TextValue(EMAIL, email)));
        Optional<Result> result = storageService.get(get);
        if(result.isPresent()){
            if(result.get().getValue(PHONE).isPresent()){
                System.out.println("Phone : " + ((TextValue) result.get().getValue(PHONE).get()).getString().get());
            }
        }else {
            System.out.println("Matching ID and NAME not found");
        }
    }

    @Override
    void getList(String name) throws ExecutionException {
        Scan scan = new Scan(new Key(new TextValue(NAME, name)));
        Scanner result = storageService.scan(scan);

        result.forEach(
            result1 -> {
                if (result1.getValue(EMAIL).isPresent()){
                    System.out.println("Email : " + ((TextValue) result1.getValue(EMAIL).get()).getString().get());
                }
                if (result1.getValue(PHONE).isPresent()){
                    System.out.println("Phone : " + ((TextValue) result1.getValue(PHONE).get()).getString().get() + "\n");

                }

        });
    }

}
