// myBoardList.js

export default {
  template: `
  <div>
    <table id="list">
      <thead>
        <tr>
          <th>글번호</th>
          <th>글제목</th>
          <th>조회수</th>
          <th>삭제</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="item in obj">
          <td>{{item.no}}</td>
          <td v-on:click="boardRead(item)">{{item.title}}</td>
          <td>{{item.view}}</td>
          <td><button v-on:click="boardDelete(item.no)">삭제</button></td>
        </tr>
      </tbody>
    </table>
    <button v-on:click="boardWrite">글쓰기</button>
  </div>
  `,
  props: ['obj'],
  methods: {
    boardWrite() {
      this.$emit('board-write');
    },
    boardDelete(no) {
      this.$emit('board-delete', no);
    },
    boardRead(board) {
      this.$emit('board-read', board);
    }
  }
}