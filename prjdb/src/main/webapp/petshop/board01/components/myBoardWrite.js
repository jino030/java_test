// myBoardWrite.js

export default {
  template: `
  <div>
    <table id="list">
      <tr>
        <th>글제목</th>
        <td><input type="text" style="width: 90%;" v-model="title"></td>
      </tr>
      <tr>
        <td colspan="2"><textarea cols="30" rows="10" style="width: 98%;" v-model="content"></textarea></td>
      </tr>
    </table>
    <button v-on:click="boardList">목록으로</button>
    <button v-on:click="boardSave">저장</button>
  </div>
  `,
  data: function(){
    return {
      title: '',
      content: ''
    }
  },
  print: function() {
    console.log('myboardwite print');
  },
  methods: {
    boardList() {
      this.$emit('board-list');
    },
    boardSave() {
      this.$emit('board-save', this.title, this.content);
    }
  }
}