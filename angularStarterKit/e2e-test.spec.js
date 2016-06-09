//protractor testing
describe('Modal A test', function() {
  it('should show all movies', function() {

    browser.get('http://localhost:9000/#/component-1/dialog-a');

    expect(element.all(by.repeater('movie in data.movies')).count()).toBe(10);
  });
});
describe('Modal Add test', function() {
  it('should add new movie', function() {
    var title, genre;

    browser.get('http://localhost:9000/#/component-1/dialog-a');
    element(by.buttonText('Add')).click();
    title = element(by.model('newMovie.title'));
    title.sendKeys('New Title');

    genre = element(by.model('newMovie.genre'));
    genre.sendKeys('New Genre');

    element(by.buttonText('save')).click();

    expect(element.all(by.repeater('movie in data.movies')).count()).toBe(11);

  });

  it('should not add new movie when click cancel', function() {
    var title, genre;

    browser.get('http://localhost:9000/#/component-1/dialog-a');
    element(by.buttonText('Add')).click();
    title = element(by.model('newMovie.title'));
    title.sendKeys('New Title');

    genre = element(by.model('newMovie.genre'));
    genre.sendKeys('New Genre');

    element(by.buttonText('cancel')).click();

    expect(element.all(by.repeater('movie in data.movies')).count()).toBe(10);
  });
});

describe('Modal Edit test', function() {
  it('should edit movie', function() {
    var title, genre;
    //browser.pause();
    browser.get('http://localhost:9000/#/component-1/dialog-a');
    element.all(by.repeater('movie in data.movies').row(2)).click();
    element(by.buttonText('Edit')).click();
    title = element(by.model('editedMovie.title'));
    title.clear().sendKeys('New Title');


    genre = element(by.model('editedMovie.genre'));
    genre.clear();
    genre.sendKeys('New Genre');
    element(by.buttonText('save')).click();
    expect(element(by.repeater('movie in data.movies').row(2).column('title')).getText()).toEqual('New Title');

  });

  it('should edit not movie when cancel is clicked', function() {
    var title, genre;
    //browser.pause();
    browser.get('http://localhost:9000/#/component-1/dialog-a');
    element.all(by.repeater('movie in data.movies').row(2)).click();
    element(by.buttonText('Edit')).click();
    title = element(by.model('editedMovie.title'));
    title.clear().sendKeys('New Title');


    genre = element(by.model('editedMovie.genre'));
    genre.clear();
    genre.sendKeys('New Genre');
    element(by.buttonText('cancel')).click();
    expect(element(by.repeater('movie in data.movies').row(2).column('title')).getText()).toEqual('The Green Mile');

  });
});

describe('Modal B test', function() {
  it('should show all movies', function() {

    browser.get('http://localhost:9000/#/component-2/dialog-b');

    expect(element.all(by.repeater('movie in data.movies')).count()).toBe(10);
  });

  it('should filter by genre', function() {
    var title, genre;
    //browser.pause();
    browser.get('http://localhost:9000/#/component-2/dialog-b');
    element.all(by.repeater('movie in data.movies').row(1)).click();

    expect(element.all(by.repeater('movie in filterByGenre(clickedMovie.genre)')).count()).toEqual(3);

  });
});
