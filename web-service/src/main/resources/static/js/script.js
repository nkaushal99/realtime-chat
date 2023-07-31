let stompClient = null;

function sendMessage() {
  let jsonOb = {
    name: localStorage.getItem("name"),
    content: $("#message-value").val()
  }
  stompClient.send("/app/chat/123", {}, JSON.stringify(jsonOb));
}

function connect() {
  let socket = new SockJS("http://localhost:8000/rtc")
  stompClient = Stomp.over(socket)
  // TODO userId input from user
  let connectHeaders = {
    userId: '8b17d41b-833f-4635-b72f-7ebb236f9d6d'
  }
  stompClient.connect(connectHeaders, function (frame) {
    console.log("Connected : " + frame)
    $("#name-from").addClass('d-none')
    $("#chat-room").removeClass('d-none')
    //subscribe
    stompClient.subscribe("/topic/123", function (response) {
      showMessage(JSON.parse(response.body))
    })
  })
}

function showMessage(message) {
  $("#message-container-table").prepend(
      `<tr><td><b>${message.name} :</b> ${message.content}</td></tr>`)
}

$(document).ready(() => {
  $("#login").click(() => {
    let name = $("#name-value").val()
    localStorage.setItem("name", name)
    $("#name-title").html(`Welcome , <b>${name} </b>`)
    connect();
  })

  $("#send-btn").click(() => {
    sendMessage()
  })

  $("#logout").click(() => {
    localStorage.removeItem("name")
    if (stompClient !== null) {
      stompClient.disconnect()
      $("#name-from").removeClass('d-none')
      $("#chat-room").addClass('d-none')
      console.log(stompClient)
    }
  })
})