package com.example.angularDemo.service;

import com.example.angularDemo.exception.MyselfException;
import com.example.angularDemo.model.UrlTestModel;
import com.example.angularDemo.repository.UrlTestModelRepository;
import com.example.angularDemo.utils.EncodeUtil;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UrlTestModelService {

    Logger logger = org.slf4j.LoggerFactory.getLogger(UrlTestModelService.class);


    private final UrlTestModelRepository repository;

    /**
     * チェック例外をスローします
     * `try catch`を使用せず、呼び出し元のコントローラーに`throws`します
     *
     * @param id
     * @return URLテストモデル
     */
    public UrlTestModel findByIdWithURIException(Long id) throws URISyntaxException {
        Optional<UrlTestModel> model = repository.findById(id);
        UrlTestModel testModel = model.get();

        if (testModel.getLogoImgUrl() == null || testModel.getLogoImgUrl().isEmpty()) {
            // 場合に応じて例外をスロー
            // URISyntaxExceptionはチェック例外のため、throws句を使用して呼び出し元で例外を処理させる
            throw new URISyntaxException(testModel.getLogoImgUrl(), "URLがnullまたは空欄です");
        }

        return model.get();
    }

    /**
     * 自作 `非チェック例外` をスローします
     * 非チェック例外である`RuntimeException`を継承しているため、メソッドに`throws`句は必要ありません
     *
     * @param id
     * @return URLテストモデル
     */
    public UrlTestModel findByIdWithSelfException(Long id) {
        Optional<UrlTestModel> model = repository.findById(id);
        UrlTestModel testModel = model.get();

        if (testModel.getLogoImgUrl() == null || testModel.getLogoImgUrl().isEmpty()) {
            // スローする例外を自作の例外クラスとします
            // TODO 自作の例外は非チェック例外の RuntimeExceptionを継承しているため、throws句は必要ありません
            throw new MyselfException("自作の例外をスロー", "CODE-MY-01");
        }
        return model.get();
    }


    /**
     * TODO ③ に対応
     *
     * @param id
     * @return
     */
    public String findByIdWithTryCatch(Long id) {
    // 以下のようにtrows句を記述しても、ハンドラーでキャッチしないと結局はエラーとなる
    // TODO ただし、
    //public String findByIdWithTryCatch(Long id) throws IllegalArgumentException {
//        Optional<UrlTestModel> model = repository.findById(id);
//        UrlTestModel testModel = model.get();

        // TODO createメソッドはライブラリ内部で例外処理されている
        //  非チェック例外である IlleagalArgumentExceptionを`throw new` する
        // URI uri = URI.create(testModel.getLogoImgUrl());

        UrlTestModel urlTestModel = new UrlTestModel();
        urlTestModel.setLogoImgUrl("http://example.com/ファイル がある");

        // ここで例外が発生する
        // Invalid URI: Illegal character in path at index 23: http://example.com/ファイル がある
        // createメソッドは非チェック例外のIlegalArgumentExceptionをthrow newするのでグローバルハンドラーで処理を記述する必要がある
        // TODO ハンドラーで処理しないとそのままエラーになるので注意が必要
        URI url = URI.create(urlTestModel.getLogoImgUrl());


            //URI url = null;
//        if (url == null) {
//            logger.error("findByIdWithTryCatchにて例外発生");
//            // 非チェック例外はメソッドthrowsを記述しなくもよい
//            // そのまま `GlobalExceptionHandler` でキャッチ可能
//            throw new IllegalArgumentException("URL cannot be null or empty");
//        }

        //return model.get();
        return  "test";
    }


    /**
     * TODO④
     *
     * @param id
     * @return
     * @throws NoSuchAlgorithmException
     */
    public String findByIdWithNoSuchAlgorithmException(Long id) throws NoSuchAlgorithmException {

        // Nosuchが発生するメソッドを呼ぶ
        //byte[] bytes = EncodeUtil.makeHashString("test","test");

        throw new NoSuchAlgorithmException();
        //return "test";
    }



}
