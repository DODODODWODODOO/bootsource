// 제목을 클릭 시 a 태그 기능 중지
// data-id 에 있는 값 가져오기

document.querySelector("tbody").addEventListener("click", (e) => {
  e.preventDefault();

  const target = e.target;

  console.log(target.dataset.id);

  fetch(`http://localhost:8080/read/${target.dataset.id}`)
    .then((response) => response.json())
    .then((data) => {
      console.log(data);

      // 디자인 영역 가져오기
      document.querySelector("#category").value = data.categoryName;
      document.querySelector("#title").value = data.title;
      document.querySelector("#publisher").value = data.publisherName;
      document.querySelector("#writer").value = data.writer;
      document.querySelector("#price").value = data.price;
      document.querySelector("#salePrice").value = data.salePrice;
      document.querySelector("#book_id").value = data.id;
    });
});

// 삭제 클릭 시 id 가져오기
document.querySelector(".btn-primary").addEventListener("click", (e) => {
  e.preventDefault(); // a 태그는 중시키겨야함

  console.log(document.querySelector("#book_id").value);
  const id = document.querySelector("#book_id").value;

  // /delete/{id} + Delete
  fetch(`/delete/${id}`, {
    method: "delete", // get 방식이 아니라면 method 는 꼭 사용
  })
    .then((response) => response.text())
    .then((data) => {
      if (data == "success") {
        alert("삭제 성공");
        location.href = "/book/list?page=1&type=&keyword=";
      }
    });
});

document.querySelector(".btn-secondary").addEventListener("click", (e) => {
  e.preventDefault(); // 태그가 가진 기능 중지(a, submit, reset)

  // form 내용 가져오기 => javascript 객체 생성
  // const myForm = document.querySelector("#myForm");
  // myForm 안에 들어있는 요소 찾기
  // myForm.querySelector("#book_id")
  const book_id = document.querySelector("#book_id").value;
  const data = {
    id: book_id,
    price: document.querySelector("#price").value,
    salePrice: document.querySelector("#salePrice").value,
  };

  console.log(data);

  // method 지정 않하면 get 으로 전송됨
  fetch(`/modify/${book_id}`, {
    method: "put",
    headers: {
      "content-type": "application/json",
    },
    body: JSON.stringify(data), // JSON.stringify() => javascript 객체 => json 타입으로 변환
  })
    .then((response) => response.text())
    .then((data) => {
      if (data == "success") {
        alert("수정성공");
        location.href = "/book/list?page=1&type=&keyword=";
      }
    });
});
