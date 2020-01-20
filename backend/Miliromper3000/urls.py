"""Miliromper3000 URL Configuration

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/3.0/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  path('', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  path('', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.urls import include, path
    2. Add a URL to urlpatterns:  path('blog/', include('blog.urls'))
"""
from django.contrib import admin
from django.urls import include, path
from rest_framework import routers
from dbAlco import views

router = routers.DefaultRouter()
router.register(r'alcohols/types', views.AlcoholTypeViewSet)
router.register(r'alcohols/general_types', views.AlcoholGeneralTypeViewSet)
router.register(r'alcohols/ratings', views.AlcoholRatingViewSet)
router.register(r'alcohols', views.AlcoholViewSet)
router.register(r'producers', views.ProducerViewSet)
router.register(r'countries', views.CountryViewSet)
router.register(r'drinks/ingredients/proportions', views.IngredientProportionViewSet)
router.register(r'drinks/ingredients', views.IngredientViewSet)
router.register(r'drinks/alcohols/proportions', views.AlcoholProportionViewSet)
router.register(r'drinks/bartender_stuff', views.BartenderStuffViewSet)
router.register(r'drinks/glasses', views.GlassViewSet)
router.register(r'drinks/ratings', views.DrinkRatingViewSet)
router.register(r'drinks', views.DrinkViewSet)
router.register(r'shops/occurrences', views.ShopOccurrenceViewSet)
router.register(r'shops', views.ShopViewSet)
router.register(r'pubs/occurrences', views.PubOccurrenceViewSet)
router.register(r'pubs/ratings', views.PubRatingViewSet)
router.register(r'pubs', views.PubViewSet)

urlpatterns = [
    path('', include(router.urls)),
    path('api-auth/', include('rest_framework.urls')),
    path('admin/', admin.site.urls),
]
