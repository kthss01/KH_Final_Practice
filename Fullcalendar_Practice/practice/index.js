document.addEventListener('DOMContentLoaded', function() {
  var calendarEl = document.getElementById('calendar');

  var calendar = new FullCalendar.Calendar(calendarEl, {
    selectable: true,
    headerToolbar: {
      left: 'prev,next today',
      center: 'title',
      right: 'dayGridMonth,timeGridWeek,timeGridDay'
    },
    editable: true, // 이거하면 수정 가능, 날짜 위치 변경, 길이 조절
    events: [
      { id: '1', title: 'Event 1', start: '2021-11-05', backgroundColor: 'green' }
    ],
    dateClick: function(info) {
      // 추후 드래그로 변경 시 날짜 클릭하면 해당 view 변경
    },
    select: function(info) {
      // 이벤트 생성
      // 입력 받아서 DB에 넣고 그에 맞게 calendar 인스턴스에도 넣기
      const title = prompt('title 입력');

      if (title) {
        calendar.addEvent({
          title,
          start: info.start,
          end: info.end,
          allDay: info.view.type === 'dayGridMonth',
          backgroundColor: 'green',
        });        
      }
    },
    eventClick: function(info) {
      // 이벤트 수정 및 삭제가 가능하게 info에 조회라고 생각
      console.log('Event: ' + info.event.title);
      console.log('Color: ' + info.event.backgroundColor);

      info.event.title = 'test'; // 이런식으로 변경 후 DB 변경
    },
  });

  calendar.render();
});