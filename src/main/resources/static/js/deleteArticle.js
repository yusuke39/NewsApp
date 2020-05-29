//new Vue ({
//    el: '#app',
//    data: {
//        deleteConfirm: '',
//    },
//    methods: {
//        deleteArticle: function(event){
//           var isDelete = confirm("DBから完全に削除してもOK？");
//           if(isDelete == true){
//           console.log(this.articleId)
//                $.ajax({
//                   url: '/article/deleteArticle',
//                     type: 'GET',
//                   })
//                    .done(function(data,textStatus,jqXHR){
//                      location.reload();
//                    }.bind(this))
//                    .fail(function(jqXHR,textStatus,errorThrown){
//                      alert("失敗しました。")
//                    }.bind(this));
//
//           } else {
//                return false;
//           }
//        }
//    }
//});
////
//////new Vue ({
//////    el: '#search',
//////      data: {
//////        genreId: $('.genreId').val(),
//////        titleName: $('.titleName').val(),
//////      },
//////      methods: {
//////           searchArticles: function(event){
//////             console.log(this.titleName),
//////             console.log(this.genreId),
//////              }
//////           }
//////      });