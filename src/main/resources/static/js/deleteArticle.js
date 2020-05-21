new Vue ({
    el: '#app',
    data: {
        deleteConfirm: '',
        articleId: $(".articleId").val(),
    },
    methods: {
        deleteArticle: function(event){
           var isDelete = confirm("DBから完全に削除してもOK？");
           if(isDelete == true){

                $.ajax({
                   url: '/article/deleteArticle',
                   data: {
                      deleteConfirm :  true,
                      articleId: this.articleId,
                     },
                     type: 'GET',
                   })
                    .done(function(data,textStatus,jqXHR){
                      location.reload();
                    }.bind(this))
                    .fail(function(jqXHR,textStatus,errorThrown){
                      alert("失敗しました。")
                    }.bind(this));

           } else {
                return false;
           }
        }
    }
})