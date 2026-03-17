
async function getSiteFeed(triggerElement) {
    let response;
    let html;
    let url = triggerElement.dataset.url;

    response = await fetch(`/site?url=${encodeURIComponent(url)}`);
    
    html = await response.text();

    if (!response.ok) {
        document.documentElement.innerHTML = html;
    } else {
        document.getElementById('feed-container').innerHTML = html;
    }
    
}

async function getRssFeed(triggerElement) {
    let response;
    let html;
    let url = triggerElement.dataset.url;

    response = await fetch(`/rss?url=${encodeURIComponent(url)}`);
    
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