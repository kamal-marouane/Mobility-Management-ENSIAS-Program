// get the search input and table
const searchInput = document.getElementById('search');
const table = document.getElementById('schools-list');

// add event listener to search input
searchInput.addEventListener('keyup', function() {
    const searchValue = this.value.toLowerCase();

    // loop through each row of the table and hide/show based on search value
    for (let i = 1; i < table.rows.length; i++) {
        const row = table.rows[i];
        let matched = false;
        for (let j = 0; j < row.cells.length; j++) {
            const cell = row.cells[j];
            const cellText = cell.innerText.toLowerCase();
            if (cellText.includes(searchValue) ||
                damerauLevenshteinDistance(searchValue, cellText) <= Math.max(searchValue.length, cellText.length) / 2) {
                matched = true;
                break;
            }
        }
        if (matched) {
            row.style.display = '';
        } else {
            row.style.display = 'none';
        }
    }
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