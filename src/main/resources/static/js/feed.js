
async function getFeed() {
    let response = await fetch('/feed');
    let html = await response.text();
    document.getElementById('feed-container').innerHTML = html;
}

function sortFeed() {
    const feed = document.getElementById("feed-container");
    const articles = Array.from(feed.querySelectorAll(".article"));

    articles.sort((a, b) => {
        return Number(b.dataset.com) - Number(a.dataset.com);
    });

    articles.forEach(article => feed.appendChild(article));
}