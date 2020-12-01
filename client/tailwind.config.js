module.exports = {
    purge: [],
    darkMode: false, // or 'media' or 'class'
    theme: {
        extend: {
            colors: {
                'light-green': '#8bc34a',
                'green': '#71a436',
                'deep-cyan': '#161B20',
                'black-01': 'rgba(0,0,0,0.1)',
                'black-05': 'rgba(0,0,0,0.5)',
                'red': '#ff5252'
            },
            fontFamily: {
                'lato': 'Lato, sans-serif',
            },
            backgroundImage: {
                'start-img': "url('img/start.png')"
            },
        },
    },
    variants: {
        extend: {},
    },
    plugins: [],
}
