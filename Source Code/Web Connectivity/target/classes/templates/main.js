const CHART = document.getElementById("my-chart");
let myChart = new Chart(CHART, {
    type: 'line',
    data:  {
        labels: Utils.months({count: 7}),
        datasets: [{
            label: 'My First Dataset',
            data: [65, 59, NaN, 48, 56, 57, 40],
            borderColor: 'rgb(75, 192, 192)',
            segment: {
                borderColor: ctx => skipped(ctx, 'rgb(0,0,0,0.2)') || down(ctx, 'rgb(192,75,75)'),
                borderDash: ctx => skipped(ctx, [6, 6]),
            }
        }]
    },
    options: genericOption
})