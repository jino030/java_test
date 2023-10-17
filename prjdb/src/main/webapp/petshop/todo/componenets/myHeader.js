// myHeader.js

export default {
  template: `
  <div style="height: 195px;">
    <h2>My Todo List</h2>
    <h3>
      할일을 등록하시려면 기존 데이터를 불러오세요.<br>
      데이터가 없다면 죄송하지만 사용할 수 없습니다.
    </h3>
    <input type="file" placeholder="파일을 선택하세요." class="file" v-on:change="loadData">
    <button v-show="btnShow" v-on:click="todoAdd" class="addBtn">등록하기</button>
  </div>
  `,
  data: function(){
    return {
      btnShow: false
    }
  },
  methods: {
    loadData(e){
      let file = e.target.files[0];

      let reader = new FileReader();
      reader.addEventListener('loadend', () => {
        let jsonData = JSON.parse(reader.result); // json 문자열 => JSON.parse() => object로 변환
        this.$emit('data', jsonData);
      })
      reader.readAsText(file);
      this.btnShow = true;
    },
    todoAdd(){
      this.$emit('todo-add');
    }
  }
}