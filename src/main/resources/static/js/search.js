//new Vue({
//  el: '.search',
//  data: {
//    genreId: $('select').val(),
//    titleName: $('#titleName').val(),
//    adminId: $('.adminId').val(),
//    articleList: "",
//    deleteConfirm: '',
//    articleId: $(".articleId").val(),
//    message: ""
//  },
//  methods: {
//    searchArticlesByGenreId: function(event){
//        $.ajax({
//            url: '/article/searchArticlesByGenreId',
//            data: {
//                genreId: this.genreId,
//                adminId: this.adminId
//            },
//            type: 'GET',
//            dataType: 'json',
//        })
//        .done(function(data,textStatus,jqXHR){
//            console.log("通信成功"),
//            console.log(data),
//            this.message = "成功しました",
//            this.articleList = data,
//            console.log(this.articleList)
//        }.bind(this))
//        .fail(function(jqXHR,textStatus,errorThrown){
//            this.message="読み込みに失敗しました";
//        }.bind(this));
//       },
//      deleteArticle: function(event){
//         var isDelete = confirm("DBから完全に削除してもOK？");
//         if(isDelete == true){
//         $.ajax({
//            url: '/article/deleteArticle',
//            data: {
//                deleteConfirm :  true,
//                articleId: this.articleId,
//              },
//              type: 'GET',
//              })
//              .done(function(data,textStatus,jqXHR){
//                   location.reload();
//                }.bind(this))
//                 .fail(function(jqXHR,textStatus,errorThrown){
//                   alert("失敗しました。")
//                  }.bind(this));
//                } else {
//                    return false;
//                }
//             }
//            }
//
////     searchArticlesByTitleName: function(event){
////          $.ajax({
////              url: '/article/searchArticlesByTitleName',
////              data: {
////                  titleName: this.titleName,
////                  adminId: this.adminId
////              },
////              type: 'GET',
////              dataType: 'json',
////          })
////          .done(function(data,textStatus,jqXHR){
////              console.log("通信成功")
////           }.bind(this))
////           .fail(function(jqXHR,textStatus,errorThrown){
////               this.message="読み込みに失敗しました";
////           }.bind(this));
////          }
//        });