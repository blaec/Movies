# Manual deploy and db-update on heroku

[Link to deployed site](http://bit.ly/g-movie)

### deploy
1. DBIProvider - change profile to `heroku` and commit-push.
1. On [Heroku](https://dashboard.heroku.com/apps) deploy selecting branch from git

### populate
1. In pgAdmin run `select * from movies;`
1. Press **F8** (download as CSV/TXT)
1. Open file with Notepad++ and copy-paste content to IntellijIDEA
   - Remove 1st row, 1st id column
   - replace `"` with `'`
   - replace `NULL` with ``
   - enclose each row in parentheses `()`
   - after each row add comma `,`
   - after last row add `;`
1. Copy-paste data back to `populate.sql` in VALUES part of query
1. Run the file selecting in target data source/schema - heroku