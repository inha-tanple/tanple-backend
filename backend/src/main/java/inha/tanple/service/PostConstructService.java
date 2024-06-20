package inha.tanple.service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import inha.tanple.domain.*;
import inha.tanple.repository.*;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostConstructService {

    private final MemberService memberService;
    private final PhotoUploadRepository photoUploadRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;
    private final CreditHistoryRepository creditHistoryRepository;
    private final WalletRepository walletRepository;


    @PostConstruct
    private void init() throws IOException, CsvException {

        loadMemberData();
        loadProductData();
        loadProductUploadData();
        loadCreditHistoryData();
        updateCreatedDates();

        Wallet wallet = walletRepository.findByMemberId(1000L).orElseThrow();
        wallet.setBalance(14720);
        walletRepository.save(wallet);
    }


    public void loadMemberData() {

        // Member
        Member adminMember = new Member();
        adminMember.setBirthDate(LocalDateTime.of(2000, 1, 1, 0, 0));
        adminMember.setEmail("admin@tanple.com");
        adminMember.setPhoneNumber("012-0000-0000");
        adminMember.setPassword("password");
        memberService.join(adminMember);

        Member userMember = new Member();
        userMember.setBirthDate(LocalDateTime.of(2000, 2, 2, 0, 0));
        userMember.setEmail("user@tanple.com");
        userMember.setPhoneNumber("013-0000-0000");
        userMember.setPassword("password");
        memberService.join(userMember);

    }

    // ************************


    public Product mapToProduct(String[] record) {

        Product product = new Product();
        product.setProductBarcode(Long.parseLong(record[0]));
        product.setCompany(record[1]);
        product.setProductName(record[2]);
        product.setPrice(Integer.parseInt(record[3]));
        product.setBusinessRegistrationNumber(record[4]);
        product.setEarnedCredit(Integer.parseInt(record[5]));
        product.setCertificationCategory(record[6]);
        product.setEarningRate(Float.parseFloat(record[7]));
        product.setRegisterStartDate(record[8]);
        product.setRegisterEndDate(record[9]);
        return product;
    }

    @Transactional
    public void loadProductData() throws IOException, CsvException {

        // CSV 파일 읽어오기
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("product_list.csv");
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        CSVReader reader = new CSVReader(inputStreamReader);
        List<String[]> records = reader.readAll();

        // CSV 데이터를 Entity 로 매핑하여 insert
        for (String[] record : records) {
            // 첫 번째 레코드는 헤더이므로 skip
            if (record[0].equals("바코드")) continue;

            Product product = this.mapToProduct(record);
            productRepository.save(product);
        }
    }

    public PhotoUpload mapToPhotoUpload(String[] record, Member member) {

        PhotoUpload photoUpload = new PhotoUpload();
        photoUpload.setId(Long.parseLong(record[0]));
        photoUpload.setMember(member);

        Product product = productRepository.findById(Long.parseLong(record[2])).orElseThrow();
        photoUpload.setProduct(product);

        photoUpload.setPhotoUrl(record[3]);
        photoUpload.setPhotoUploadStatus(PhotoUploadStatus.SUCCESS);
        return photoUpload;
    }

    @Transactional
    public void loadProductUploadData() throws IOException, CsvException {

        // CSV 파일 읽어오기
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("photo_upload_list.csv");
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        CSVReader reader = new CSVReader(inputStreamReader);
        List<String[]> records = reader.readAll();

        Member member = memberRepository.findById(1000L).orElseThrow();

        // CSV 데이터를 Entity 로 매핑하여 insert
        for (String[] record : records) {
            // 첫 번째 레코드는 헤더이므로 skip
            if (record[0].equals("photo_upload_id")) continue;

            PhotoUpload photoUpload = this.mapToPhotoUpload(record, member);
            photoUploadRepository.save(photoUpload);
        }
    }

    //

    public CreditHistory mapToCreditHistory(String[] record, Wallet wallet) {
        CreditHistory creditHistory = new CreditHistory();
        creditHistory.setWallet(wallet);
        creditHistory.setCreatedDate(LocalDateTime.parse(record[0], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        creditHistory.setCredit(Integer.parseInt(record[1]));
        creditHistory.setPlusACC(Integer.parseInt(record[2]));
        creditHistory.setBalance(Integer.parseInt(record[3]));
        creditHistory.setCreditType(record[5].equals("적립") ? CreditType.적립 : CreditType.소비);
        creditHistory.setDetail(record[6]);

        if (record[8] != null && !record[8].isEmpty()) {
            PhotoUpload photoUpload = new PhotoUpload();
            photoUpload.setId(Long.parseLong(record[8]));
            creditHistory.setPhotoUpload(photoUpload);
        }

        // creditMethod 설정 로직 추가 (예: PURCHASE, EXCHANGE, DONATION 등)
        if (record[6].equals("환전")) {
            creditHistory.setCreditMethod(CreditMethod.EXCHANGE);
        } else if (record[6].equals("기부")) {
            creditHistory.setCreditMethod(CreditMethod.DONATION);
        } else {
            creditHistory.setCreditMethod(CreditMethod.PURCHASE);
        }

        return creditHistory;
    }

    @Transactional
    public void loadCreditHistoryData() throws IOException, CsvException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("credit_history_list.csv");
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        CSVReader reader = new CSVReader(inputStreamReader);
        List<String[]> records = reader.readAll();

        Wallet wallet = memberRepository.findById(1000L).orElseThrow().getWallet();

        for (String[] record : records) {
            if (record[0].equals("created_date")) continue;

            CreditHistory creditHistory = this.mapToCreditHistory(record, wallet);
            creditHistoryRepository.save(creditHistory);
        }
    }

    @Transactional
    public void updateCreatedDates() throws IOException, CsvException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("credit_history_list.csv");
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        CSVReader reader = new CSVReader(inputStreamReader);
        List<String[]> records = reader.readAll();

        for (String[] record : records) {
            if (record[0].equals("created_date")) continue;
            CreditHistory creditHistory = creditHistoryRepository.findById(Long.parseLong(record[7])).orElseThrow();

            LocalDateTime createdDate = LocalDateTime.parse(record[0], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            creditHistory.setCreatedDate(createdDate);

            creditHistoryRepository.save(creditHistory);
        }

    }
}
