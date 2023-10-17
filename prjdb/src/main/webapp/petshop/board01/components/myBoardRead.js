// myBoardRead.js

export default {
  template: `
  <div>
    <table id="list">
      <tr>
        <th style="width: 50px;">글제목</th>
        <td>{{obj.title}}</td>
      </tr>
      <tr style="height: 300px;">
        <td colspan="2">{{obj.content}}</td>
      </tr>
    </table>
    <button v-on:click="boardList">목록으로</button>
  </div>
  `,
  props: ['obj'],
  methods: {
    boardList(){
      this.$emit('board-list');
    }
  }
}