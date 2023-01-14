Web API
獲取GitHub用户列表
https://api.github.com/users?since=0&per_page=5
https://api.github.com/users?since=5&per_page=5
https://api.github.com/users?since=19&per_page=5
@param since 這裏的since是上一個列表中最後一個User的id，以這個id作為since的值，請求下一列表的數據

撰寫順序:
User
UserDao, UserDatabase
Api, RetrofitClient
UserBoundaryCallback, UserViewModel, UserAdapter
MainActivity