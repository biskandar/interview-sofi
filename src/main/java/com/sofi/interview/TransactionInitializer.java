package com.sofi.interview;

import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
class TransactionInitializer implements ApplicationRunner {

    private TransactionReactiveRepository transactionRepository;

    public TransactionInitializer(TransactionReactiveRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        /*
        String[] record = null;
        try (CSVReader csvReader = new CSVReader(new FileReader("./coding_challenge_data.csv"))) {
            while ((record = csvReader.readNext()) != null) {
                for (String cell : record) {
                    System.out.print(cell + " ");
                }
                System.out.println();
            }
        } */

        try (Reader reader = Files.newBufferedReader(Paths.get("./coding_challenge_data.csv"))) {
            new CsvToBeanBuilder<Transaction>(reader)
                    .withType(Transaction.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build().parse().forEach(t -> {
                transactionRepository.save(TransactionBuilder.aTransaction()
                        .withId(t.getId())
                        .withMerchantId(t.getMerchantId())
                        .withMerchantName(t.getMerchantName())
                        .withUserId(t.getUserId())
                        .withPrice(t.getPrice())
                        .withPurchaseDate(t.getPurchaseDate())
                        .build()).subscribe();
            });
        }

    }

}
