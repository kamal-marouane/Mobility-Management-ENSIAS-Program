const searchInput = document.getElementById("search");
const tableBody = document.querySelector('#students-list tbody');
const tableRows = tableBody.querySelectorAll('tr');

searchInput.addEventListener("input", function(event) {
    const searchTerm = event.target.value.toLowerCase();
    tableRows.forEach(row => {
        const studentName = row.querySelector('td:nth-child(3)').textContent;
        const nameParts = studentName.split(' ');
        const firstName = nameParts[0];
        const lastName = nameParts[1];
        const reversedName = lastName.trim()+' '+firstName.trim();
        if (damerauLevenshteinDistance(searchTerm, firstName) <= Math.max(searchTerm.length, firstName.length) / 2
            || damerauLevenshteinDistance(searchTerm, lastName) <= Math.max(searchTerm.length, lastName.length) / 2
            || damerauLevenshteinDistance(searchTerm, studentName) <= Math.max(searchTerm.length, studentName.length) / 2
            || damerauLevenshteinDistance(searchTerm, reversedName) <= Math.max(searchTerm.length, reversedName.length) / 2
            || studentName.includes(searchTerm)) {
            row.style.display = "table-row";
        } else {
            row.style.display = "none";
        }

    });
});
function damerauLevenshteinDistance(a, b) {
    const d = [];
    let i, j, cost;
    const m = a.length, n = b.length;

    for (i = 0; i <= m; i++) {
        d[i] = [];
        d[i][0] = i;
    }
    for (j = 0; j <= n; j++) {
        d[0][j] = j;
    }
    for (i = 1; i <= m; i++) {
        for (j = 1; j <= n; j++) {
            cost = a[i - 1] === b[j - 1] ? 0 : 1;
            d[i][j] = Math.min(d[i - 1][j] + 1, d[i][j - 1] + 1, d[i - 1][j - 1] + cost);
            if (i > 1 && j > 1 && a[i - 1] === b[j - 2] && a[i - 2] === b[j - 1]) {
                d[i][j] = Math.min(d[i][j], d[i - 2][j - 2] + cost);
            }
        }
    }
    return d[m][n];
}