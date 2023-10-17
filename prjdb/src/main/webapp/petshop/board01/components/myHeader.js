// myHeader.js

export default {
  template: `
  <div>
    <h3>간단한 게시판</h3>
    <p>게시판의 데이터는 board.json을 활용해서 초기화 합니다.</p>
    <input type="file" v-on:change="loadData">
  </div>
  `,
  methods: {
    loadData(e){
      console.log(e.target.files[0]);
      let file = e.target.files[0];
      console.log(file);

      let reader = new FileReader();
      reader.addEventListener('loadend', () => {
        let jsonData = JSON.parse(reader.result);
        console.log(jsonData); // json 문자열 => JSON.parse() => object로 변환
        this.$emit('data', jsonData);
      })
      reader.readAsText(file);
    }
  }
}