exports.config = {

    seleniumAddress: 'http://localhost:4444/wd/hub',
    baseUrl: 'http://localhost:9000/',
    specs : ['e2e-test.spec.js'],

    jasmineNodeOpts: {
        defaultTimeoutInterval: 250000,
        showColors: true,
        isVerbose: true
    }
};
