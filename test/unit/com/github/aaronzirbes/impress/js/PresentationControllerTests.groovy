package com.github.aaronzirbes.impress.js



import org.junit.*
import grails.test.mixin.*

@TestFor(PresentationController)
@Mock(Presentation)
class PresentationControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/presentation/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.presentationInstanceList.size() == 0
        assert model.presentationInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.presentationInstance != null
    }

    void testSave() {
        controller.save()

        assert model.presentationInstance != null
        assert view == '/presentation/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/presentation/show/1'
        assert controller.flash.message != null
        assert Presentation.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/presentation/list'


        populateValidParams(params)
        def presentation = new Presentation(params)

        assert presentation.save() != null

        params.id = presentation.id

        def model = controller.show()

        assert model.presentationInstance == presentation
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/presentation/list'


        populateValidParams(params)
        def presentation = new Presentation(params)

        assert presentation.save() != null

        params.id = presentation.id

        def model = controller.edit()

        assert model.presentationInstance == presentation
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/presentation/list'

        response.reset()


        populateValidParams(params)
        def presentation = new Presentation(params)

        assert presentation.save() != null

        // test invalid parameters in update
        params.id = presentation.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/presentation/edit"
        assert model.presentationInstance != null

        presentation.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/presentation/show/$presentation.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        presentation.clearErrors()

        populateValidParams(params)
        params.id = presentation.id
        params.version = -1
        controller.update()

        assert view == "/presentation/edit"
        assert model.presentationInstance != null
        assert model.presentationInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/presentation/list'

        response.reset()

        populateValidParams(params)
        def presentation = new Presentation(params)

        assert presentation.save() != null
        assert Presentation.count() == 1

        params.id = presentation.id

        controller.delete()

        assert Presentation.count() == 0
        assert Presentation.get(presentation.id) == null
        assert response.redirectedUrl == '/presentation/list'
    }
}
