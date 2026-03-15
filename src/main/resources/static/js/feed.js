
async function getFeed() {
    let response;
    let html;

    response = await fetch('/feed');
    html = await response.text();

    if (!response.ok) {
        document.documentElement.innerHTML = html;
    } else {
        document.getElementById('feed-container').innerHTML = html;
    }
    
}

function sortFeed() {
    const feed = document.getElementById("feed-container");
    const articles = Array.from(feed.querySelectorAll(".article"));

    articles.sort((a, b) => {
        return Number(b.dataset.com) - Number(a.dataset.com);
    });

    articles.forEach(article => feed.appendChild(article));
}