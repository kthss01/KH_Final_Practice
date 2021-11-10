document.addEventListener('DOMContentLoaded', function() {
  var calendarEl = document.getElementById('calendar');

  var calendar = new FullCalendar.Calendar(calendarEl, {
    headerToolbar: {
      left: 'prev,next today',
      center: 'title',
      right: 'dayGridMonth,timeGridWeek'
    },
    initialDate: '2021-11-12',
    businessHours: true, // display business hours
    editable: true,
    events: [
      {
        title: 'Business Lunch',
        start: '2021-11-03T13:00:00',
        constraint: 'businessHours'
      },
      {
        title: 'Meeting',
        start: '2021-11-13T11:00:00',
        constraint: 'availableForMeeting', // defined below
        color: '#257e4a'
      },
      {
        title: 'Conference',
        start: '2021-11-18',
        end: '2021-11-20'
      },
      {
        title: 'Party',
        start: '2021-11-29T20:00:00'
      },

      // areas where "Meeting" must be dropped
      {
        groupId: 'availableForMeeting',
        start: '2021-11-11T10:00:00',
        end: '2021-11-11T16:00:00',
        display: 'background'
      },
      {
        groupId: 'availableForMeeting',
        start: '2021-11-13T10:00:00',
        end: '2021-11-13T16:00:00',
        display: 'background'
      },

      // red areas where no events can be dropped
      {
        start: '2021-11-24',
        end: '2021-11-28',
        overlap: false,
        display: 'background',
        color: '#ff9f89'
      },
      {
        start: '2021-11-06',
        end: '2021-11-08',
        overlap: false,
        display: 'background',
        color: '#ff9f89'
      }
    ]
  });

  calendar.render();
});