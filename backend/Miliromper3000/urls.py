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
router.register(r'alcohols/types', views.AlcoholTypeViewSet, basename="alcohol_types")
router.register(r'alcohols/general_types', views.AlcoholGeneralTypeViewSet, basename="alcohol_general_types")
router.register(r'alcohols/ratings', views.AlcoholRatingViewSet, basename="alcohol_ratings")
router.register(r'alcohols', views.AlcoholViewSet, basename="alcohols")
router.register(r'producers', views.ProducerViewSet, basename="producers")
router.register(r'countries', views.CountryViewSet, basename="countries")
router.register(r'drinks/ingredients/proportions', views.IngredientProportionViewSet, basename="ingredients_proportions")
router.register(r'drinks/ingredients', views.IngredientViewSet, basename="ingredients")
router.register(r'drinks/alcohols/proportions', views.AlcoholProportionViewSet, basename="alcohol_proportions")
router.register(r'drinks/bartender_stuff', views.BartenderStuffViewSet,basename="bartender_stuff")
router.register(r'drinks/glasses', views.GlassViewSet, basename="glasses")
router.register(r'drinks/ratings', views.DrinkRatingViewSet, basename="drinks_ratings")
router.register(r'drinks', views.DrinkViewSet, basename="drinks")
router.register(r'shops/occurrences', views.ShopOccurrenceViewSet, basename="shops_occurrences")
router.register(r'shops', views.ShopViewSet, basename="shops")
router.register(r'pubs/occurrences', views.PubOccurrenceViewSet, basename="pubs_occurrences")
router.register(r'pubs/ratings', views.PubRatingViewSet, basename="pubs_ratings")
router.register(r'pubs', views.PubViewSet, basename="pubs")
router.register(r'users', views.UserViewSet, basename="users")

urlpatterns = [
    path('', include(router.urls)),
    path('api-auth/', include('rest_framework.urls')),
    path('admin/', admin.site.urls),
    path('alcohols/ratings/avg/<int:_id>/', views.alcohol_average_rating),
    path('drinks/ratings/avg/<int:_id>/', views.drink_average_rating),

]
