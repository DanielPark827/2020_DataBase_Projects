package code;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws Exception {

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your PostgreSQL JDBC Driver? Include in your library path!");
            e.printStackTrace();
            return;
        }
        System.out.println("PostgreSQL JDBC Driver Registered!");
        /// if you have a error in this part, check jdbc driver(.jar file)

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://127.0.0.1:5432/project_movie", "postgres", "cse3207");
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return;
        }
        /// if you have a error in this part, check DB information (db_name, user name, password)

        if (connection != null) {
            System.out.println(connection);
            System.out.println("You made it, take control your database now!");
        } else {
            System.out.println("Failed to make connection!");
        }


        Statement stmt = connection.createStatement();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try	{//Create Table
        	System.out.println("Create Table!");
        	//movie
    		String createSql1 = "CREATE TABLE  movie(movieID integer primary key, movieName varchar(30),"
    				+ " releaseYear char(5), releaseMonth char(3), releaseDate char(3), "
    				+ "publisherName varchar(30), avgRate numeric(2,1))";
    		stmt.executeUpdate(createSql1);
    		System.out.println("movie created!");
    		//director
    		String createSql2 = "CREATE TABLE director(directorID integer primary key, directorName varchar(20),"
    				+ "dateOfBirth char(12), dateOfDeath char(12))";
    		stmt.executeUpdate(createSql2);
    		System.out.println("director created!");
    		//actor
    		String createSql3 = "CREATE TABLE actor(actorID integer primary key, actorName varchar(20),"
    				+ "dateOfBirth char(12), dateOfDeath char(12), gender varchar(6))";
    		stmt.executeUpdate(createSql3);
    		System.out.println("actor created!");
    		//customer
    		String createSql4 = "CREATE TABLE customer(customerID integer primary key, customerName varchar(20),"
    				+ "dateOfBirth char(12), gender char(6))";
    		stmt.executeUpdate(createSql4);
    		System.out.println("customer created!");
    		//award
    		String createSql5 = "CREATE TABLE award(awardID integer, awardName varchar(30), primary key(awardID))";
    		stmt.executeUpdate(createSql5);
    		System.out.println("award created!");
    		//genre
    		String createSql6 = "CREATE TABLE genre(genreName varchar(15) primary key)";
    		stmt.executeUpdate(createSql6);
    		System.out.println("genre created!");
    		//movieGenre
    		String createSql7 = "CREATE TABLE movieGenre(movieID integer, genreName varchar(15),"
    				+ "foreign key(movieID) references movie(movieID) on delete cascade,"
    				+ "foreign key(genreName) references genre(genreName) on delete cascade,"
    				+ "primary key(movieID, genreName))";
    		stmt.executeUpdate(createSql7);
    		System.out.println("movieGenre created!");
    		//actorObtain
    		String createSql8 = "create table actorObtain(actorID integer, awardID integer,"
    				+ "year char(4),primary key(actorID,awardID), foreign key(actorID) references actor(actorID) on delete cascade,"
    				+ " foreign key(awardID)  references award(awardID) on delete cascade)";
    		stmt.executeUpdate(createSql8);
    		System.out.println("actorObtain created!");
    		//movieObtain
    		String createSql9 = "create table movieObtain(movieID integer, awardID integer,"
    				+ "year char(4), primary key(movieID, awardID),"
    				+ "foreign key(movieID) references movie(movieID) on delete cascade, foreign key(awardID) references award(awardID) on delete cascade)";
    		stmt.executeUpdate(createSql9);
    		System.out.println("movieObtain created!");
    		//directorObtain
    		String createSql10 = "create table directorObtain(directorID integer, awardID integer,"
    				+ "year char(4),primary key(directorID, awardID) , foreign key(directorID) references director(directorID) on delete cascade,"
    				+ "foreign key(awardID) references award(awardID) on delete cascade)";
    		stmt.executeUpdate(createSql10);
    		System.out.println("directorObtain created!");
    		//casting
    		String createSql11 = "create table casting(movieID integer, actorID integer, role varchar(30),"
    				+ "primary key(movieID, actorID),foreign key(movieID) references movie(movieID) on delete cascade,"
    				+ "foreign key(actorID) references actor(actorID) on delete cascade)";
    		stmt.executeUpdate(createSql11);
    		System.out.println("casting created!");
    		//make
    		String createSql12 = "create table make(movieID integer,directorID integer,"
    				+ "primary key(movieID, directorID),"
    				+ "foreign key(movieID) references movie(movieID) on delete cascade,"
    				+ "foreign key(directorID) references director(directorID) on delete cascade)";
    		stmt.executeUpdate(createSql12);
    		System.out.println("make created!");
    		//customerRate
    		String createSql13 = "create table customerRate(customerID integer, movieID integer, rate numeric(2,1),"
    				+ "primary key(customerID, movieID),"
    				+ "foreign key(customerID) references customer(customerID) on delete cascade, foreign key(movieID) references movie(movieID) on delete cascade)";
    		stmt.executeUpdate(createSql13);
    		System.out.println("customerRate created!");
    		System.out.println("Table Created Successfully!");
        	//connection.close();
        } catch (SQLException e) {
        	e.printStackTrace();
        }
        
        //stmt = connection.createStatement();
        String[] director_name = {"Tim Burton", "David Fincher", "Christopher Nolan"};
        String[] director_date_of_birth = {"1958.8.25","1962.8.28","1970.7.30"};
        String[] actor_name = {"Johnny Depp", "Winona Ryder", "Anne Hathaway", "Christian Bale", "Heath Ledger", "Jesse Eisenberg", "Andrew Garfield", "Fionn Whitehead", "Tom Hardy"};
        String[] actor_date_of_birth = {"1963.6.9","1971.10.29","1982.11.12","1974.1.30","1979.4.4", "1983.10.5", "1983.8.20", "1997.7.18", "1977.9.15"};
        String[] actor_date_of_death = {null,null,null,null,"2008.1.22",null,null,null,null};
        String[] actor_gender = {"Male", "Female", "Female", "Male", "Male", "Male", "Male", "Male", "Male"};
        String[] customer_name = {"Bob", "John", "Jack", "Jill", "Bell"};
        String[] customer_date_of_birth = {"1997.11.14", "1978.01.23", "1980.05.04", "1981.04.17", "1990.05.14"};
        String[] customer_gender = {"Male", "Male", "Male", "Female","Female"};
        String[] movie_name = {"Edward Scissorhands", "Alice In Wonderland", "The Social Network", "The Dark Knight", "Dunkirk"};
        int[] movie_release_year = {1991,2010,2010,2008,2017};
        int[] movie_release_month = {6,3,11,8,7};
        int[] movie_release_date = {29,4,18,6,13};
        String[] movie_publisher_name = {"20th Century Fox Presents","Korea Sony Pictures","Korea Sony Pictures","Warner Brothers Korea","Warner Brothers Korea"};
        String[] genre_name = {"Fantasy", "Romance", "Adventure", "Family", "Drama","Action", "Mystery", "Thriller","War"};
        int[] make_director_ID = {1,1,2,3,3};
        int[] casting_movie_ID = {1,1,2,2,3,3,4,4,5,5};
        int[] casting_actor_ID = {1,2,1,3,6,7,4,5,8,9};
        String[] casting_role = {"Main actor", "Main actor", "Main actor", "Main actor", "Main actor", "Supporting Actor", "Main actor", "Main actor", "Main actor", "Main actor"};
        int[] movie_genre_movie_ID = {1,1,2,2,2,3,4,4,4,4,5,5,5,5};
        String[] movie_genre_genre_name = {"Fantasy", "Romance", "Fantasy", "Adventure", "Family", "Drama", "Action", "Drama", "Mystery", "Thriller", "Action", "Drama", "Thriller", "War"};
        try {//Initialize Data
        	System.out.println("Initialize Data!");
        	//director
        	pst = connection.prepareStatement("insert into director(directorID, directorName, dateofBirth, dateOfDeath)"
        			+ "VALUES (?,?,?,null)");
        	for(int i = 0; i < 3; i++)
        	{
        		pst.setInt(1, i+1);
            	pst.setString(2, director_name[i]);
            	pst.setString(3,director_date_of_birth[i]);
            	pst.executeUpdate();
        	}
        	System.out.println("director Initialize!");
        	//actor
        	pst = connection.prepareStatement("insert into actor(actorID, actorName,dateOfBirth, dateOfDeath, gender)"
        			+ "VALUES (?,?,?,?,?)");
        	for(int i = 0; i < 9; i++)
        	{
        		pst.setInt(1, i+1);
        		pst.setString(2, actor_name[i]);
        		pst.setString(3, actor_date_of_birth[i]);
        		pst.setString(4, actor_date_of_death[i]);
        		pst.setString(5, actor_gender[i]);
        		pst.executeUpdate();
        	}
        	System.out.println("actor Initialize!");
        	//customer
        	pst = connection.prepareStatement("insert into customer(customerID, customerName, dateOfBirth, gender)"
        			+ "VALUES (?,?,?,?)");
        	for(int i = 0; i < 5; i++)
        	{
        		pst.setInt(1, i+1);
        		pst.setString(2, customer_name[i]);
        		pst.setString(3, customer_date_of_birth[i]);
        		pst.setString(4, customer_gender[i]);
        		pst.executeUpdate();
        	}
        	System.out.println("customer Initialize!");
        	//movie
        	pst = connection.prepareStatement("insert into movie(movieID, movieName,releaseYear, releaseMonth, releaseDate, publisherName, avgRate)"
        			+ "VALUES (?,?,?,?,?,?,0.0)");
        	int k = 1;
        	for(int i =0; i < 5; i++)
        	{
        		pst.setInt(1, i+1);
        		pst.setString(2, movie_name[i]);
        		pst.setInt(3, movie_release_year[i]);
        		pst.setInt(4, movie_release_month[i]);
        		pst.setInt(5, movie_release_date[i]);
        		pst.setString(6,movie_publisher_name[i]);
        		pst.executeUpdate();
        	}
        	System.out.println("movie Initialize!");
        	//genre
        	pst = connection.prepareStatement("insert into genre(genreName)"
        			+ "VALUES (?)");
        	for(int i = 0; i < 9; i++)
        	{
        		pst.setString(1, genre_name[i]);
        		pst.executeUpdate();
        	}
        	System.out.println("genre Initialize!");
        	//make
        	pst = connection.prepareStatement("insert into make(movieID, directorID)"
        			+ "VALUES (?,?)");
        	for(int i = 0; i < 5; i++)
        	{
        		pst.setInt(1, i+1);
        		pst.setInt(2, make_director_ID[i]);
        		pst.executeUpdate();
        	}
        	System.out.println("make Initialize!");
        	//casting
        	pst = connection.prepareStatement("insert into casting(movieID, actorID, role)"
        			+ "VALUES (?,?,?)");
        	for(int i = 0; i < 10; i++)
        	{
        		pst.setInt(1,casting_movie_ID[i]);
        		pst.setInt(2, casting_actor_ID[i]);
        		pst.setString(3,casting_role[i]);
        		pst.executeUpdate();
        	}
        	System.out.println("casting Initialize!");
        	//moviegenre
        	pst = connection.prepareStatement("insert into movieGenre(movieID, genreName)"
        			+ "VALUES (?,?)");
        	for(int i = 0; i < 14; i++)
        	{
        		pst.setInt(1, movie_genre_movie_ID[i]);
        		pst.setString(2, movie_genre_genre_name[i]);
        		pst.executeUpdate();
        	}
        	System.out.println("movieGenre Initialize!");
        	System.out.println("Data Initialized!");
        	//connection.close();
        }catch (SQLException e) {
        	e.printStackTrace();
        }
        
        //stmt = connection.createStatement();
        try {
        	//2.1 Winona Ryder won the ¡°Best supporting actor¡± award in 1994
            System.out.println("\n2.1 Winona Ryder won the ¡°Best supporting actor¡± award in 1994");
            System.out.println("Translated SQL : insert into award values(1, 'Best supporting actor')");
            System.out.println("Translated SQL : insert into actorObtain values(2,1,'1994')");
            String query_2_1_1 = "insert into award values(1, 'Best supporting actor')";
            String query_2_1_2 = "insert into actorObtain values(2,1,'1994')";
            stmt.executeUpdate(query_2_1_1);
            stmt.executeUpdate(query_2_1_2);
            
            rs = stmt.executeQuery("SELECT * FROM award");
            System.out.println("-----<award>------");
            System.out.println(String.format("%-30s%-30s", "awardID", "awardName"));
            while(rs.next())
            {
            	int a = rs.getInt(1);
            	String b = rs.getString(2);
            	System.out.println(String.format("%-30d%-30s", a, b));
            }
            System.out.println("\n");
            
            rs = stmt.executeQuery("SELECT * FROM actorObtain");
            System.out.println("-----<actorObtain>------");
            System.out.println(String.format("%-30s%-30s%-30s", "actorID","awardID", "year"));
            while(rs.next())
            {
            	int a = rs.getInt(1);
            	int b = rs.getInt(2);
            	String c = rs.getString(3);
            	System.out.println(String.format("%-30d%-30d%-30s", a, b, c));
            }
            System.out.println("\n");	
            
            //2.2 Andrew Garfield won the ¡°Best supporting actor¡± award in 2011
            System.out.println("\n2.2 Andrew Garfield won the ¡°Best supporting actor¡± award in 2011");
            System.out.println("Translated SQL : insert into actorObtain values(7,1,'2011')");
            String query_2_2_1 = "insert into actorObtain values(7,1,'2011')";
            stmt.executeUpdate(query_2_2_1);
            
            rs = stmt.executeQuery("SELECT * FROM actorObtain");
            System.out.println("-----<actorObtain>------");
            System.out.println(String.format("%-30s%-30s%-30s", "actorID","awardID", "year"));
            while(rs.next())
            {
            	int a = rs.getInt(1);
            	int b = rs.getInt(2);
            	String c = rs.getString(3);
            	System.out.println(String.format("%-30d%-30d%-30s", a, b, c));
            }
            System.out.println("\n");
            
          //2.3 Jesse Eisenberg won the ¡°Best main actor¡± award in 2011
            System.out.println("\n2.3 Jesse Eisenberg won the ¡°Best main actor¡± award in 2011");
            System.out.println("Translated SQL : insert into award values(2, 'Best main actor')");
            System.out.println("Translated SQL : insert into actorObtain values(6,2,'2011')");
            String query_2_3_1 = "insert into award values(2, 'Best main actor')";
            String query_2_3_2 = "insert into actorObtain values(6,2,'2011')";
            stmt.executeUpdate(query_2_3_1);
            stmt.executeUpdate(query_2_3_2);
            
            rs = stmt.executeQuery("SELECT * FROM award");
            System.out.println("-----<award>------");
            System.out.println(String.format("%-30s%-30s", "awardID", "awardName"));
            while(rs.next())
            {
            	int a = rs.getInt(1);
            	String b = rs.getString(2);
            	System.out.println(String.format("%-30d%-30s", a, b));
            }
            System.out.println("\n");
            
            rs = stmt.executeQuery("SELECT * FROM actorObtain");
            System.out.println("-----<actorObtain>------");
            System.out.println(String.format("%-30s%-30s%-30s", "actorID","awardID", "year"));
            while(rs.next())
            {
            	int a = rs.getInt(1);
            	int b = rs.getInt(2);
            	String c = rs.getString(3);
            	System.out.println(String.format("%-30d%-30d%-30s", a, b, c));
            }
            System.out.println("\n");	
            
         // 2.4 Johnny Depp won the ¡°Best villain actor¡± award in 2011
            System.out.println("\n2.4 Johnny Depp won the ¡°Best villain actor¡± award in 2011");
            System.out.println("Translated SQL : insert into award values(3, 'Best villain actor')");
            System.out.println("Translated SQL : insert into actorObtain values(1,3,'2011')");
            String query_2_4_1 = "insert into award values(3, 'Best villain actor')";
            String query_2_4_2 = "insert into actorObtain values(1,3,'2011')";
            stmt.executeUpdate(query_2_4_1);
            stmt.executeUpdate(query_2_4_2);
            
            rs = stmt.executeQuery("SELECT * FROM award");
            System.out.println("-----<award>------");
            System.out.println(String.format("%-30s%-30s", "awardID", "awardName"));
            while(rs.next())
            {
            	int a = rs.getInt(1);
            	String b = rs.getString(2);
            	System.out.println(String.format("%-30d%-30s", a, b));
            }
            System.out.println("\n");
            
            rs = stmt.executeQuery("SELECT * FROM actorObtain");
            System.out.println("-----<actorObtain>------");
            System.out.println(String.format("%-30s%-30s%-30s", "actorID","awardID", "year"));
            while(rs.next())
            {
            	int a = rs.getInt(1);
            	int b = rs.getInt(2);
            	String c = rs.getString(3);
            	System.out.println(String.format("%-30d%-30d%-30s", a, b, c));
            }
            System.out.println("\n");
            
         // 2.5 Edward Scissorhands won the ¡°Best fantasy movie¡± award in 1991
            System.out.println("\n2.5 Edward Scissorhands won the ¡°Best fantasy movie¡± award in 1991");
            System.out.println("Translated SQL : insert into award values(4, 'Best fantasy movie')");
            System.out.println("Translated SQL : insert into movieObtain values(1,4,'1911')");
            String query_2_5_1 = "insert into award values(4, 'Best fantasy movie')";
            String query_2_5_2 = "insert into movieObtain values(1,4,'1911')";
            stmt.executeUpdate(query_2_5_1);
            stmt.executeUpdate(query_2_5_2);
            
            rs = stmt.executeQuery("SELECT * FROM award");
            System.out.println("-----<award>------");
            System.out.println(String.format("%-30s%-30s", "awardID", "awardName"));
            while(rs.next())
            {
            	int a = rs.getInt(1);
            	String b = rs.getString(2);
            	System.out.println(String.format("%-30d%-30s", a, b));
            }
            System.out.println("\n");
            
            rs = stmt.executeQuery("SELECT * FROM movieObtain");
            System.out.println("-----<movieObtain>------");
            System.out.println(String.format("%-30s%-30s%-30s", "movieID","awardID", "year"));
            while(rs.next())
            {
            	int a = rs.getInt(1);
            	int b = rs.getInt(2);
            	String c = rs.getString(3);
            	System.out.println(String.format("%-30d%-30d%-30s", a, b, c));
            }
            System.out.println("\n");
            
         // 2.6 The Dark Knight won the ¡°Best picture¡± award in 2009
            System.out.println("\n2.6 The Dark Knight won the ¡°Best picture¡± award in 2009");
            System.out.println("Translated SQL : insert into award values(5, 'Best picture')");
            System.out.println("Translated SQL : insert into movieObtain values(4,5,'2009')");
            String query_2_6_1 = "insert into award values(5, 'Best picture')";
            String query_2_6_2 = "insert into movieObtain values(4,5,'2009')";
            stmt.executeUpdate(query_2_6_1);
            stmt.executeUpdate(query_2_6_2);
            
            rs = stmt.executeQuery("SELECT * FROM award");
            System.out.println("-----<award>------");
            System.out.println(String.format("%-30s%-30s", "awardID", "awardName"));
            while(rs.next())
            {
            	int a = rs.getInt(1);
            	String b = rs.getString(2);
            	System.out.println(String.format("%-30d%-30s", a, b));
            }
            System.out.println("\n");
            
            rs = stmt.executeQuery("SELECT * FROM movieObtain");
            System.out.println("-----<movieObtain>------");
            System.out.println(String.format("%-30s%-30s%-30s", "movieID","awardID", "year"));
            while(rs.next())
            {
            	int a = rs.getInt(1);
            	int b = rs.getInt(2);
            	String c = rs.getString(3);
            	System.out.println(String.format("%-30d%-30d%-30s", a, b, c));
            }
            System.out.println("\n");
            
         // 2.7 Alice In Wonderland won the ¡°Best fantasy movie¡± award in 2011
            System.out.println("\n2.7 Alice In Wonderland won the ¡°Best fantasy movie¡± award in 2011");
            System.out.println("Translated SQL : insert into movieObtain values(2,4,'2011')");
            String query_2_7_1 = "insert into movieObtain values(2,4,'2011')";
            stmt.executeUpdate(query_2_7_1);
            
            rs = stmt.executeQuery("SELECT * FROM movieObtain");
            System.out.println("-----<movieObtain>------");
            System.out.println(String.format("%-30s%-30s%-30s", "movieID","awardID", "year"));
            while(rs.next())
            {
            	int a = rs.getInt(1);
            	int b = rs.getInt(2);
            	String c = rs.getString(3);
            	System.out.println(String.format("%-30d%-30d%-30s", a, b, c));
            }
            System.out.println("\n");
            
         // 2.8 David Fincher won the ¡°Best director¡± award in 2011
            System.out.println("\n2.8 David Fincher won the ¡°Best director¡± award in 2011");
            System.out.println("Translated SQL : insert into award values(6, 'Best director')");
            System.out.println("Translated SQL : insert into directorObtain values(2,6,'2011')");
            String query_2_8_1 = "insert into award values(6, 'Best director')";
            String query_2_8_2 = "insert into directorObtain values(2,6,'2011')";
            stmt.executeUpdate(query_2_8_1);
            stmt.executeUpdate(query_2_8_2);
            
            rs = stmt.executeQuery("SELECT * FROM award");
            System.out.println("-----<award>------");
            System.out.println(String.format("%-30s%-30s", "awardID", "awardName"));
            while(rs.next())
            {
            	int a = rs.getInt(1);
            	String b = rs.getString(2);
            	System.out.println(String.format("%-30d%-30s", a, b));
            }
            System.out.println("\n");
            
            rs = stmt.executeQuery("SELECT * FROM movieObtain");
            System.out.println("-----<directorObtain>------");
            System.out.println(String.format("%-30s%-30s%-30s", "directorID","awardID", "year"));
            while(rs.next())
            {
            	int a = rs.getInt(1);
            	int b = rs.getInt(2);
            	String c = rs.getString(3);
            	System.out.println(String.format("%-30d%-30d%-30s", a, b, c));
            }
            System.out.println("\n");
            
            System.out.println(" Insert Completed! ");
            //connection.close();
        } catch (SQLException e) {
        	e.printStackTrace();
        }
        
        //stmt = connection.createStatement();
        try {
        	//3.1 Bob rates 5 to ¡°The Dark Knight¡±
            System.out.println("\n3.1 Bob rates 5 to 'The Dark Knight'");
            System.out.println("Translated SQL : insert into customerRate values(1,4,5.0)");
            System.out.println("Translated SQL : update movie set avgRate = (select avg(rate) from customerRate where movieName ='The Dark Knight')");
            String query_3_1_1 = "insert into customerRate values(1,4,5.0)";
            String query_3_1_2 = "update movie" + " set avgRate = (select avg(rate) from customerRate"
					+ " where movieName ='The Dark Knight')";
            stmt.executeUpdate(query_3_1_1);
            stmt.executeUpdate(query_3_1_2);
            
            rs = stmt.executeQuery("SELECT * FROM customerRate");
            System.out.println("-----<customerRate>------");
            System.out.println(String.format("%-30s%-30s%-30s", "customerID","movieID","Rate"));
            while(rs.next())
            {
            	int a = rs.getInt(1);
            	int b = rs.getInt(2);
            	double c = rs.getDouble(3);
            	System.out.println(String.format("%-30d%-30d%-30f", a, b, c));
            }
            System.out.println("\n");
            
            rs = stmt.executeQuery("SELECT * FROM movie");
            System.out.println("-----<movie>------");
            System.out.println(String.format("%-30s%-30s%-30s%-30s%-30s%-30s%-30s", "movieID","movieName","releaseYear","releaseMonth","releaseDate", "publisherName","avgRate"));
            while(rs.next())
            {
            	int a = rs.getInt(1);
            	String b = rs.getString(2);
            	String c = rs.getString(3);
            	String d = rs.getString(4);
            	String e = rs.getString(5);
            	String f = rs.getString(6);
            	double g = rs.getDouble(7);
            	System.out.println(String.format("%-30d%-30s%-30s%-30s%-30s%-30s%-30f", a, b, c,d,e,f,g));
            }
            System.out.println("\n");
            
         //3.2 Bell rates 5 to the movies whose director is ¡°Tim Burton¡±.
            System.out.println("\n3.2 Bell rates 5 to the movies whose director is 'Tim Burton'.");
            System.out.println("Translated SQL : insert into customerRate select 5 as customerID, movieID, 5.0 as Rate from make where directorID = 1");
            System.out.println("Translated SQL : update movie m set avgRate = (select avg(rate) from (select movieID, rate from make natural join customerRate where m.movieID=movieID) s) "
            		+ "\nwhere m.movieID in (select movieID from make where directorID=1)");
            String query_3_2_1 = "insert into customerRate"
            		+ " select 5 as customerID, movieID, 5.0 as Rate from make where directorID = 1";   		
            String query_3_2_2 = "update movie m "
            		+ "set avgRate = (select avg(rate) "
            		+ "from (select movieID, rate "
            		+ "from make natural join customerRate "
            		+ "where m.movieID=movieID) s) "
            		+ "where m.movieID in (select movieID from make where directorID=1)";
            stmt.executeUpdate(query_3_2_1);
            stmt.executeUpdate(query_3_2_2);
            
            rs = stmt.executeQuery("SELECT * FROM customerRate");
            System.out.println("-----<customerRate>------");
            System.out.println(String.format("%-30s%-30s%-30s", "customerID","movieID","Rate"));
            while(rs.next())
            {
            	int a = rs.getInt(1);
            	int b = rs.getInt(2);
            	double c = rs.getDouble(3);
            	System.out.println(String.format("%-30d%-30d%-30f", a, b, c));
            }
            System.out.println("\n");
            
            rs = stmt.executeQuery("SELECT * FROM movie");
            System.out.println("-----<movie>------");
            System.out.println(String.format("%-30s%-30s%-30s%-30s%-30s%-30s%-30s", "movieID","movieName","releaseYear","releaseMonth","releaseDate", "publisherName","avgRate"));
            while(rs.next())
            {
            	int a = rs.getInt(1);
            	String b = rs.getString(2);
            	String c = rs.getString(3);
            	String d = rs.getString(4);
            	String e = rs.getString(5);
            	String f = rs.getString(6);
            	double g = rs.getDouble(7);
            	System.out.println(String.format("%-30d%-30s%-30s%-30s%-30s%-30s%-30f", a, b, c,d,e,f,g));
            }
            System.out.println("\n");
            
          //3.3 Jill rates 4 to the movies whose main actor is female.
            System.out.println("\n3.3 Jill rates 4 to the movies whose main actor is female.");
            System.out.println("Translated SQL : insert into customerRate select distinct 4 as customerID, movieID, 4.0 as rate from casting natural join actor where gender = 'Female'");
            System.out.println("Translated SQL : update movie m set avgRate = (select avg(rate) from (select movieID, rate from actor natural join casting natural join customerRate where m.movieID=movieID)s) "
            		+ "\nwhere m.movieID in (select movieID from casting natural join actor where gender='Female')");
            String query_3_3_1 = "insert into customerRate "
            		+ "select distinct 4 as customerID, movieID, 4.0 as rate from casting natural join actor "
            		+ "where gender = 'Female'";
            String query_3_3_2 = "update movie m "
            		+ "set avgRate = (select avg(rate) from (select movieID, rate "
            		+ "from actor natural join casting natural join customerRate "
            		+ "where m.movieID=movieID)s) "
            		+ "where m.movieID in (select movieID from casting natural join actor where gender='Female')";
            stmt.executeUpdate(query_3_3_1);
            stmt.executeUpdate(query_3_3_2);
            
            rs = stmt.executeQuery("SELECT * FROM customerRate");
            System.out.println("-----<customerRate>------");
            System.out.println(String.format("%-30s%-30s%-30s", "customerID","movieID","Rate"));
            while(rs.next())
            {
            	int a = rs.getInt(1);
            	int b = rs.getInt(2);
            	double c = rs.getDouble(3);
            	System.out.println(String.format("%-30d%-30d%-30f", a, b, c));
            }
            System.out.println("\n");
            
            rs = stmt.executeQuery("SELECT * FROM movie");
            System.out.println("-----<movie>------");
            System.out.println(String.format("%-30s%-30s%-30s%-30s%-30s%-30s%-30s", "movieID","movieName","releaseYear","releaseMonth","releaseDate", "publisherName","avgRate"));
            while(rs.next())
            {
            	int a = rs.getInt(1);
            	String b = rs.getString(2);
            	String c = rs.getString(3);
            	String d = rs.getString(4);
            	String e = rs.getString(5);
            	String f = rs.getString(6);
            	double g = rs.getDouble(7);
            	System.out.println(String.format("%-30d%-30s%-30s%-30s%-30s%-30s%-30f", a, b, c,d,e,f,g));
            }
            System.out.println("\n");
            
          //3.4 Jack rates 4 to the fantasy movies.
            System.out.println("3.4 Jack rates 4 to the fantasy movies.");
            System.out.println("Translated SQL : insert into customerRate select distinct 3 as customerID, movieID, 4.0 as rate from movie natural join movieGenre where genreName='Fantasy'");
            System.out.println("Translated SQL : update movie m set avgRate = (select avg(rate) from (select movieID, rate from movieGenre natural join customerRate where m.movieID = movieID)s) "
            		+ "\nwhere m.movieID in (select movieID from movieGenre where genreName='Fantasy')");
            String query_3_4_1 = "insert into customerRate "
            		+ "select distinct 3 as customerID, movieID, 4.0 as rate from movie natural join movieGenre "
            		+ "where genreName='Fantasy'";
            String query_3_4_2 = "update movie m "
            		+ "set avgRate = (select avg(rate) from (select movieID, rate "
            		+ "from movieGenre natural join customerRate "
            		+ "where m.movieID = movieID)s)"
            		+ "where m.movieID in (select movieID from movieGenre where genreName='Fantasy')";
            stmt.executeUpdate(query_3_4_1);
            stmt.executeUpdate(query_3_4_2);
            
            rs = stmt.executeQuery("SELECT * FROM customerRate");
            System.out.println("-----<customerRate>------");
            System.out.println(String.format("%-30s%-30s%-30s", "customerID","movieID","Rate"));
            while(rs.next())
            {
            	int a = rs.getInt(1);
            	int b = rs.getInt(2);
            	double c = rs.getDouble(3);
            	System.out.println(String.format("%-30d%-30d%-30f", a, b, c));
            }
            System.out.println("\n");
            
            rs = stmt.executeQuery("SELECT * FROM movie");
            System.out.println("-----<movie>------");
            System.out.println(String.format("%-30s%-30s%-30s%-30s%-30s%-30s%-30s", "movieID","movieName","releaseYear","releaseMonth","releaseDate", "publisherName","avgRate"));
            while(rs.next())
            {
            	int a = rs.getInt(1);
            	String b = rs.getString(2);
            	String c = rs.getString(3);
            	String d = rs.getString(4);
            	String e = rs.getString(5);
            	String f = rs.getString(6);
            	double g = rs.getDouble(7);
            	System.out.println(String.format("%-30d%-30s%-30s%-30s%-30s%-30s%-30f", a, b, c,d,e,f,g));
            }
            System.out.println("\n");
            
          //3.5 John rates 5 to the movies whose director won the ¡°Best director¡± award
            System.out.println("\n3.5 John rates 5 to the movies whose director won the ¡°Best director¡± award");
            System.out.println("Translated SQL : insert into customerRate select distinct 2 as customerID, movieID, 5.0 as Rate from make natural join directorObtain where awardID = 6");
            System.out.println("Translated SQL : update movie m set avgRate = (select avg(rate) from (select movieID, rate from customerRate natural join directorObtain natural join make where m.movieID=movieID)s) "
            		+ "\nwhere m.movieID in (select movieID from customerRate natural join directorObtain natural join make)");
            String query_3_5_1 = "insert into customerRate "
            		+ "select distinct 2 as customerID, movieID, 5.0 as Rate from make natural join directorObtain "
            		+ "where awardID = 6";
            String query_3_5_2 = "update movie m "
            		+ "set avgRate = (select avg(rate) from (select movieID, rate "
            		+ "from customerRate natural join directorObtain natural join make "
            		+ "where m.movieID=movieID)s) "
            		+ "where m.movieID in (select movieID from customerRate natural join directorObtain natural join make)";
            stmt.executeUpdate(query_3_5_1);
            stmt.executeUpdate(query_3_5_2);
            
            rs = stmt.executeQuery("SELECT * FROM customerRate");
            System.out.println("-----<customerRate>------");
            System.out.println(String.format("%-30s%-30s%-30s", "customerID","movieID","Rate"));
            while(rs.next())
            {
            	int a = rs.getInt(1);
            	int b = rs.getInt(2);
            	double c = rs.getDouble(3);
            	System.out.println(String.format("%-30d%-30d%-30f", a, b, c));
            }
            System.out.println("\n");
            
            rs = stmt.executeQuery("SELECT * FROM movie");
            System.out.println("-----<movie>------");
            System.out.println(String.format("%-30s%-30s%-30s%-30s%-30s%-30s%-30s", "movieID","movieName","releaseYear","releaseMonth","releaseDate", "publisherName","avgRate"));
            while(rs.next())
            {
            	int a = rs.getInt(1);
            	String b = rs.getString(2);
            	String c = rs.getString(3);
            	String d = rs.getString(4);
            	String e = rs.getString(5);
            	String f = rs.getString(6);
            	double g = rs.getDouble(7);
            	System.out.println(String.format("%-30d%-30s%-30s%-30s%-30s%-30s%-30f", a, b, c,d,e,f,g));
            }
            System.out.println("\n");
            
            System.out.println(" Insert Completed! ");
        	//connection.close();
            
        } catch (SQLException e) {
        	e.printStackTrace();
        }
        
        
        //stmt = connection.createStatement();
        //4. Select the names of the movies whose actor are dead.
        try {
        	System.out.println("\n4. Select the names of the movies whose actor are dead.");
        	System.out.println("Translated SQL : select movieName casting natural join actor natural join movie where dateofDeath is not null ");
            rs = stmt.executeQuery("select movieName "
            		+ "from casting natural join actor natural join movie "
            		+ "where dateofDeath is not null ");
            System.out.println("Output:");
            while(rs.next())
            {
            	String a = rs.getString(1);
            	System.out.println(a);
            }
            System.out.println();
            System.out.println("Completed!\n\n");
            //5. Select the names of the directors who cast the same actor more than once.
            System.out.println("5. Select the names of the directors who cast the same actor more than once.");
            System.out.println("Translated SQL : select directorName from casting natural join make natural join director "
            		+ "\ngroup by actorID, directorID, directorName having count(*) > 1");
            rs = stmt.executeQuery("select directorName "
            		+ "from casting natural join make natural join director "
            		+ "group by actorID, directorID, directorName having count(*) > 1");
            System.out.println("Output:");
            while(rs.next())
            {
            	String a = rs.getString(1);
            	System.out.println(a);
            }
            System.out.println();
            System.out.println("Completed!\n\n");
            //6.  Select the names of the movies and the genres, where movies have the common genre.
            System.out.println("6.  Select the names of the movies and the genres, where movies have the common genre.");
            System.out.println("Translated SQL : select movieName, genreName from movie natural join movieGenre where genreName "
            		+ "\nin (select genreName from movieGenre group by genreName having count(genreName) > 1)");
            rs = stmt.executeQuery("select movieName, genreName " + 
            		"from movie natural join movieGenre " + 
            		"where genreName in (select genreName "
            		+ "from movieGenre "
            		+ "group by genreName "
            		+ "having count(genreName) > 1)");
            System.out.println("Output:");
            while(rs.next())
            {
            	String a = rs.getString(1);
            	String b = rs.getString(2);
            	System.out.println(String.format("%-30s%-30s", a, b));
            }
            System.out.println();
            System.out.println("Completed!\n\n");
            
            
            System.out.println("7. Delete the movies which did not get any award (including all directors and actors) and delete data from\r\n" + 
            		"related tables.");
            System.out.println("Translated SQL : delete from movie where movie.movieID not in (select movieID from casting natural join actorObtain) "
            		+ "\nand movie.movieID not in (select movieID from make natural join directorObtain)");
            String query_7 = "delete from movie "
            		+ "where movie.movieID not in (select movieID from casting natural join actorObtain) "
            		+ "and movie.movieID not in (select movieID from make natural join directorObtain)";
            stmt.executeUpdate(query_7);
            System.out.println("Output:");
            rs = stmt.executeQuery("SELECT * FROM movie");
            System.out.println("-----<movie>------");
            System.out.println(String.format("%-30s%-30s%-30s%-30s%-30s%-30s%-30s", "movieID","movieName","releaseYear","releaseMonth","releaseDate", "publisherName","avgRate"));
            while(rs.next())
            {
            	int a = rs.getInt(1);
            	String b = rs.getString(2);
            	String c = rs.getString(3);
            	String d = rs.getString(4);
            	String e = rs.getString(5);
            	String f = rs.getString(6);
            	double g = rs.getDouble(7);
            	System.out.println(String.format("%-30d%-30s%-30s%-30s%-30s%-30s%-30f", a, b, c,d,e,f,g));
            }
            System.out.println("\n");
            rs = stmt.executeQuery("SELECT * FROM movieGenre");
            System.out.println("-----<movieGenre>------");
            System.out.println(String.format("%-30s%-30s", "movieID","genreName"));
            while(rs.next())
            {
            	int a = rs.getInt(1);
            	String b = rs.getString(2);
            	System.out.println(String.format("%-30d%-30s", a, b));
            }
            System.out.println("\n");
            
            rs = stmt.executeQuery("SELECT * FROM movieObtain");
            System.out.println("-----<movieObtain>------");
            System.out.println(String.format("%-30s%-30s%-30s", "movieID","awardID","Year"));
            while(rs.next())
            {
            	int a = rs.getInt(1);
            	int b = rs.getInt(2);
            	String c = rs.getString(3);
            	System.out.println(String.format("%-30d%-30d%-30s", a, b, c));
            }
            System.out.println("\n");
            
            rs = stmt.executeQuery("SELECT * FROM casting");
            System.out.println("-----<casting>------");
            System.out.println(String.format("%-30s%-30s%-30s", "movieID","actorID","role"));
            while(rs.next())
            {
            	int a = rs.getInt(1);
            	int b = rs.getInt(2);
            	String c = rs.getString(3);

            	System.out.println(String.format("%-30d%-30d%-30s", a, b, c));
            }
            System.out.println("\n");
            rs = stmt.executeQuery("SELECT * FROM make");
            System.out.println("-----<make>------");
            System.out.println(String.format("%-30s%-30s", "movieID","directorID"));
            while(rs.next())
            {
            	int a = rs.getInt(1);
            	int b = rs.getInt(2);

            	System.out.println(String.format("%-30d%-30d", a, b));
            }
            System.out.println("\n");
            rs = stmt.executeQuery("SELECT * FROM customerRate");
            System.out.println("-----<customerRate>------");
            System.out.println(String.format("%-30s%-30s%-30s", "customerID","movieID","rate"));
            while(rs.next())
            {
            	int a = rs.getInt(1);
            	int b = rs.getInt(2);
            	double c = rs.getDouble(3);
            	System.out.println(String.format("%-30d%-30d%-30f", a, b, c));
            }
            
            //8. Delete all customers and delete data from related tables.
            System.out.println("8. Delete all customers and delete data from related tables.");
            System.out.println("Translated SQL : delete from customere");
            System.out.println("Translated SQL : update movie set avgRate = (select avg(rate) from customerRate group by movieID having customerRate.movieID=movie.movieID)");
            String query_8_1 = "delete from customer";
            String query_8_2 = "update movie "
            		+ "set avgRate = 0";
            stmt.executeUpdate(query_8_1);
            stmt.executeUpdate(query_8_2);
            rs = stmt.executeQuery("SELECT * FROM movie");
            System.out.println("-----<movie>------");
            System.out.println(String.format("%-30s%-30s%-30s%-30s%-30s%-30s%-30s", "movieID","movieName","releaseYear","releaseMonth","releaseDate", "publisherName","avgRate"));
            while(rs.next())
            {
            	int a = rs.getInt(1);
            	String b = rs.getString(2);
            	String c = rs.getString(3);
            	String d = rs.getString(4);
            	String e = rs.getString(5);
            	String f = rs.getString(6);
            	double g = rs.getDouble(7);
            	System.out.println(String.format("%-30d%-30s%-30s%-30s%-30s%-30s%-30f", a, b, c,d,e,f,g));
            }
            rs = stmt.executeQuery("SELECT * FROM customerRate");
            System.out.println("-----<customerRate>------");
            System.out.println(String.format("%-30s%-30s%-30s", "customerID","movieID","rate"));
            while(rs.next())
            {
            	int a = rs.getInt(1);
            	int b = rs.getInt(2);
            	double c = rs.getDouble(3);
            	System.out.println(String.format("%-30d%-30d%-30f", a, b, c));
            }
            System.out.println("Completed!\n\n");
            
            
            
            System.out.println("9. Delete all tables and data.");
            System.out.println("Translated SQL : drop table if exists movie cascade");
            System.out.println("Translated SQL : drop table if exists director cascade");
            System.out.println("Translated SQL : drop table if exists actor cascade");
            System.out.println("Translated SQL : drop table if exists award cascade");
            System.out.println("Translated SQL : drop table if exists genre cascade");
            System.out.println("Translated SQL : drop table if exists movieGenre cascad");
            System.out.println("Translated SQL : drop table if exists movieObtain cascade");
            System.out.println("Translated SQL : drop table if exists actorObtain cascade");
            System.out.println("Translated SQL : drop table if exists directorObtain cascade");
            System.out.println("Translated SQL : drop table if exists casting cascade");
            System.out.println("Translated SQL : drop table if exists make cascade");
            System.out.println("Translated SQL : drop table if exists customerRate cascade");
            System.out.println("Translated SQL : drop table if exists customer cascade");
 
            String query_9_1 = "drop table if exists movie cascade";
            String query_9_2 = "drop table if exists director cascade";
            String query_9_3 = "drop table if exists actor cascade";
            String query_9_4 = "drop table if exists award cascade";
            String query_9_5 = "drop table if exists genre cascade";
            String query_9_6 = "drop table if exists movieGenre cascade";
            String query_9_7 = "drop table if exists movieObtain cascade";
            String query_9_8 = "drop table if exists actorObtain cascade";
            String query_9_9 = "drop table if exists directorObtain cascade";
            String query_9_10 = "drop table if exists casting cascade";
            String query_9_11= "drop table if exists make cascade";
            String query_9_12 = "drop table if exists customerRate cascade";
            String query_9_13 = "drop table if exists customer cascade";
            stmt.executeUpdate(query_9_1);
            stmt.executeUpdate(query_9_2);
            stmt.executeUpdate(query_9_3);
            stmt.executeUpdate(query_9_4);
            stmt.executeUpdate(query_9_5);
            stmt.executeUpdate(query_9_6);
            stmt.executeUpdate(query_9_7);
            stmt.executeUpdate(query_9_8);
            stmt.executeUpdate(query_9_9);
            stmt.executeUpdate(query_9_10);
            stmt.executeUpdate(query_9_11);
            stmt.executeUpdate(query_9_12);
            stmt.executeUpdate(query_9_13);
            System.out.println("Delete Completed!\n\n");
            connection.close();
        } catch (SQLException e) {
        	e.printStackTrace();
        	}
        }
    
}