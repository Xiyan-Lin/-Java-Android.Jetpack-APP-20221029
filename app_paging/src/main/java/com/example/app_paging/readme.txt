Web API
https://api.itbook.store/
@GET("search/{query}/{page}")
Example:
https://api.itbook.store/1.0/search/java/1
https://api.itbook.store/1.0/search/vb/2

撰寫順序:
model/BookApiRespone, model/Book, model/Query
api/Api, api/RetrofirClient
paging/BookDataSource, paging/BookDataSourceFactory
paging/BookListViewModel
BookListActivity
MainActivity


回家作業 Homework
https://rickandmortyapi.com/api/character?page=2

