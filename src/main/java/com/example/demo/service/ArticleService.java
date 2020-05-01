package com.example.demo.service;

import com.example.demo.domain.Article;
import com.example.demo.form.ArticleRegisterForm;
import com.example.demo.mapper.ArticleMapper;
import com.google.cloud.storage.Acl;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


@Service
public class ArticleService {


    @Autowired
    ArticleMapper articleMapper;

    /**
     * CloudStorage認証
     */
    private static Storage storage = null;

    //start init
    static {
        storage = StorageOptions.getDefaultInstance().getService();
    }


    /**
     * 記事をDBへ登録する.
     * @param articleRegisterForm
     */
    public void registerArticle(ArticleRegisterForm articleRegisterForm, String imageUrl){

        Article article = new Article();
        article.setTitle(articleRegisterForm.getTitle());
        article.setImage(imageUrl);
        article.setContent(articleRegisterForm.getContent());
        article.setGenre_id(articleRegisterForm.getGenre_id());
        article.setAdmin_id(12);

        articleMapper.insertArticle(article);

    }


    /**
     *ファイルをuploadしてURlを返す.
     * @param filePart
     * @param bucketName
     * @return
     * @throws IOException
     */
    public String uploadFile(Part filePart, final String bucketName) throws IOException {

        final String fileName = filePart.getSubmittedFileName();

        BlobInfo blobInfo =
                storage.create(
                        BlobInfo
                                .newBuilder(bucketName,fileName)
                                .setAcl(new ArrayList<>(Arrays.asList(Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER)))).build(),
                        filePart.getInputStream());

        return blobInfo.getMediaLink();
    }

}
