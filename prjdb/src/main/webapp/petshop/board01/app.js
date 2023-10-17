// app.js
import myBoardWrite from './components/myBoardWrite.js';
import myHeader from './components/myHeader.js'
import myBoardList from './components/myBoardList.js'
import myBoardRead from './components/myBoardRead.js';



const template = `
<div>
  <my-header v-on:data="getData"></my-header>
  <my-board-list v-if="listOK"  v-bind:obj="dataArray.board"
                                v-on:board-write="boardWrite"
                                v-on:board-delete="boardDelete"
                                v-on:board-read="boardReadEvent"></my-board-list>
  <my-board-read v-if="readOK"  v-bind:obj="board"
                                v-on:board-list="boardList"></my-board-read>
  <my-board-write v-if="writeOK"  v-on:board-list="boardList"
                                  v-on:board-save="boardSave"></my-board-write>
</div>
`;


new Vue({
  el: '#app',
  template: template,
  data: {
    listOK: false,
    readOK: false,
    writeOK: false,
    dataArray: {}, // board.json
    board: {}
  },
  components: {
    'my-header': myHeader,
    'my-board-list': myBoardList,
    'my-board-read': myBoardRead,
    'my-board-write': myBoardWrite
  },
  methods: {
    boardList() { //목록화면
      this.listOK = true;
      this.readOK = false;
      this.writeOK = false;
    },
    boardRead() { //상세화면
      this.listOK = false;
      this.readOK = true;
      this.writeOK = false;
    },
    boardWrite() { //글쓰기화면
      this.listOK = false;
      this.readOK = false;
      this.writeOK = true;
    },
    boardSave(title, content) {
      //no, view=0
      let no = 1;
      let view = 0;
      if (this.dataArray.board.length != 0) {
        let idx = this.dataArray.board.length - 1;
        no = Number(this.dataArray.board[idx].no) + 1;
      }
      let board = {no, title, content, view};
      this.dataArray.board.push(board);

      this.boardList(); //목록화면 실행
    },
    boardDelete(no) {
      // no 삭제 후 dataArray에 변경된 값을 저장..
      this.dataArray.board.forEach((el,idx) => {
        if(el.no == no){
          this.dataArray.board.splice(idx, 1);
        }
      });
    },
    boardReadEvent(board) {
      this.boardRead();
      board.view++;
      this.board = board;
    },
    getData(json){
      this.dataArray = json;
      this.boardList();
    }
  },
  created: function () {
    //board.json 데이터 목록 출력
    // fetch('./data/board.json')
    //   .then(resolve => resolve.json())
    //   .then(result => {
    //     this.dataArray = result;
    //     this.boardList(); //목록페이지 open
    //   })
    //   .catch(err => {
    //     console.log('parsing error: ', err);
    //   })
    // console.log(this.dataArray);
  }
})
