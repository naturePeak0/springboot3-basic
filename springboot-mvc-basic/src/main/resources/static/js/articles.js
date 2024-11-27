const createButton = document.getElementById("create-btn")

if (createButton) {
    createButton.addEventListener("click", event => {
        fetch(`/api/articles`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                title: document.getElementById("title").value,
                content: document.getElementById("content").value,
            })
        })
            .then(() => {
                alert("생성이 완료되었습니다.")
                location.replace(`/articles`)
            })
    });
}

const deleteButton = document.getElementById("delete-btn")

if (deleteButton) {
    deleteButton.addEventListener("click", event => {
        let id = document.getElementById("article-id").value;
        console.log(id)
        fetch(`/api/articles/${id}`, {
            method: "DELETE"
        })
            .then(() => {
                alert("삭제되었습니다.")
                location.replace("/articles")
            })
    })
}

const modifyButton = document.getElementById("modify-btn")

if (modifyButton) {
    modifyButton.addEventListener("click", event => {
       let params = new URLSearchParams(location.search)
       let id = params.get("id");

       fetch(`/api/articles/${id}`, {
           method: "PUT",
           headers: {
               "Content-Type": "application/json",
           },
           body: JSON.stringify({
               title: document.getElementById("title").value,
               content: document.getElementById("content").value,
           })
       })
           .then(() => {
               alert("수정이완료되었습니다.")
               location.replace(`/articles/${id}`)
           })
    });
}