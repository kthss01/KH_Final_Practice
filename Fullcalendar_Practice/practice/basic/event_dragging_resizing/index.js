document.addEventListener('DOMContentLoaded', function() {
  var calendarEl = document.getElementById('calendar');

  var calendar = new FullCalendar.Calendar(calendarEl, {
    timeZone: 'UTC',
    headerToolbar: {
      left: 'prev,next today',
      center: 'title',
      right: 'dayGridMonth,timeGridWeek,timeGridDay'
    },
    editable: true, // 이거만 하면 되는듯
    dayMaxEvents: true, // when too many events in a day, show the popover
    events: 'https://fullcalendar.io/demo-events.json?overload-day',
    drop: function(info) {
      console.log(info);
    },
    // eventDragStop: function(info) {
    //   console.log('eventDragStop', info);

    //   console.log(info.event);
    //   console.log(info.event.start);
    //   console.log(info.event.end);
    // },
    eventDrop: function(info) {
      console.log('eventDrop', info);
      console.log(info.event);
      console.log(info.event.start);
      console.log(info.event.end);
    },
    // eventResizeStop: function(info) {
    //   console.log('eventResizeStop', info);
    //   console.log(info.event);
    //   console.log(info.event.start);
    //   console.log(info.event.end);
    // },
    eventResize: function(info) {
      console.log('eventResize', info);
      console.log(info.event);
      console.log(info.event.start);
      console.log(info.event.end);
    }
  });

  calendar.render();
});