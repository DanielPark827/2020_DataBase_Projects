# 2020 데이터베이스 프로젝트 모음.

### 💻 Technology
+ Java 

### 🔭 Work
+ MidTerm_JDBC : Java 환경에서 DB를 구축하고 이를 웹서비스로 배포하였습니다.
+ Fianl_BianryTree : 디스크 단위의 데이터 적재 및 탐색 기능을 구현하기 위해 C++환경에서 BinaryTree를 구현하여 비트 단위로 데이터를 핸들하였습니다.

### 🌱 For more details of BinaryTree
1.	구현 기능
: index creation, insertion, point search, range search, print 모두 구현하였습니다.
2.	구현 설명
<BTree 클래스>
- point search / range search / print
1) point search
Depth를 이용해 non-leaf, leaf node를 구분하여 key는 leaf noe에서 찾고, 그 안에서 별도의 탐색을 통해 entry를 return하는 방식을 취했습니다.
2) range search
Vector 스트럭쳐를 이용하여 range내의 key값을 모두 이 vector에 넣어 return하는 방식을 채택하였습니다. 	기본적으로 트리를 내려가는 방식은 point search와 동일하고, 그 이후에 range 내의 key값들을 vector에 넣는 추가적인 연산을 실행하였습니다.
3) print
Leaf가 root냐, childeren이냐, non-leaf이냐에 따라 다른 프로세싱을 하였습니다. 출력되는 key값은 ostream을 이용하여 출력하였습니다.
3.	컴파일 및 실행
    	Visual studio를 이용하여 C++를 통해 작성하였습니다.



