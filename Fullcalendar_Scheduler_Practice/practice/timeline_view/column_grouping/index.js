document.addEventListener('DOMContentLoaded', function() {
  var calendarEl = document.getElementById('calendar');

  var calendar = new FullCalendar.Calendar(calendarEl, {
    headerToolbar: {
      left: 'today prev,next',
      center: 'title',
      right: 'resourceTimelineDay,resourceTimelineWeek'
    },
    aspectRatio: 1.5,
    initialView: 'resourceTimelineDay',
    resourceAreaWidth: '40%',
    resourceAreaColumns: [
      {
        group: true,
        field: 'building',
        headerContent: 'Building'
      },
      {
        field: 'title',
        headerContent: 'Room'
      },
      {
        field: 'occupancy',
        headerContent: 'Occupancy'
      }
    ],
    resources: [
      { id: 'a', building: '460 Bryant', title: 'Auditorium A', occupancy: 40 },
      { id: 'b', building: '460 Bryant', title: 'Auditorium B', occupancy: 40 },
      { id: 'c', building: '460 Bryant', title: 'Auditorium C', occupancy: 40 },
      { id: 'd', building: '460 Bryant', title: 'Auditorium D', occupancy: 40 },
      { id: 'e', building: '460 Bryant', title: 'Auditorium E', occupancy: 40 },
      { id: 'f', building: '460 Bryant', title: 'Auditorium F', occupancy: 40 },
      { id: 'g', building: '564 Pacific', title: 'Auditorium G', occupancy: 40 },
      { id: 'h', building: '564 Pacific', title: 'Auditorium H', occupancy: 40 },
      { id: 'i', building: '564 Pacific', title: 'Auditorium I', occupancy: 40 },
      { id: 'j', building: '564 Pacific', title: 'Auditorium J', occupancy: 40 },
      { id: 'k', building: '564 Pacific', title: 'Auditorium K', occupancy: 40 },
      { id: 'l', building: '564 Pacific', title: 'Auditorium L', occupancy: 40 },
      { id: 'm', building: '564 Pacific', title: 'Auditorium M', occupancy: 40 },
      { id: 'n', building: '564 Pacific', title: 'Auditorium N', occupancy: 40 },
      { id: 'o', building: '564 Pacific', title: 'Auditorium O', occupancy: 40 },
      { id: 'p', building: '564 Pacific', title: 'Auditorium P', occupancy: 40 },
      { id: 'q', building: '564 Pacific', title: 'Auditorium Q', occupancy: 40 },
      { id: 'r', building: '564 Pacific', title: 'Auditorium R', occupancy: 40 },
      { id: 's', building: '564 Pacific', title: 'Auditorium S', occupancy: 40 },
      { id: 't', building: '564 Pacific', title: 'Auditorium T', occupancy: 40 },
      { id: 'u', building: '564 Pacific', title: 'Auditorium U', occupancy: 40 },
      { id: 'v', building: '564 Pacific', title: 'Auditorium V', occupancy: 40 },
      { id: 'w', building: '564 Pacific', title: 'Auditorium W', occupancy: 40 },
      { id: 'x', building: '564 Pacific', title: 'Auditorium X', occupancy: 40 },
      { id: 'y', building: '564 Pacific', title: 'Auditorium Y', occupancy: 40 },
      { id: 'z', building: '564 Pacific', title: 'Auditorium Z', occupancy: 40 }
    ]
  });

  calendar.render();
});